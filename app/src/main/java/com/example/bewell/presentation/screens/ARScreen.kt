package com.example.bewell.presentation.screens

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.filament.Engine
import com.google.ar.core.Anchor
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.loaders.MaterialLoader
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.model.ModelInstance
import io.github.sceneview.node.CubeNode
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberView

@Composable
fun CameraPermissionHandler(
    onPermissionGranted: @Composable () -> Unit
) {
    val context = LocalContext.current
    var showRationale by remember { mutableStateOf(false) }

    val permission = Manifest.permission.CAMERA
    val permissionState = checkPermissionState(context, permission)

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (!isGranted) {
            showRationale = true
        }
    }

    when {
        permissionState -> {
            onPermissionGranted()
        }
        showRationale -> {
            PermissionRationaleDialog(
                onDismiss = { showRationale = false },
                onConfirm = {
                    showRationale = false
                    permissionLauncher.launch(permission)
                }
            )
        }
        else -> {
            SideEffect {
                permissionLauncher.launch(permission)
            }
        }
    }
}

@Composable
fun PermissionRationaleDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Camera Permission Required") },
        text = { Text("Camera access is required for AR functionality. Please grant the permission to continue.") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Request Permission")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

fun checkPermissionState(context: Context, permission: String): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        permission
    ) == PackageManager.PERMISSION_GRANTED
}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ARScreen(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
//        CameraPermissionHandler {
//
//        }

        ARComposeScreen()
    }
}


@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
@Composable
fun ARComposeScreen() {

    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine) //
    val materialLoader = rememberMaterialLoader(engine)
    val cameraNode = rememberARCameraNode(engine)
    val childNodes = rememberNodes() //list of items in-case of different items
    val view = rememberView(engine) //compose view
    val collision = rememberCollisionSystem(view)
    val planeRenderer = remember { mutableStateOf(true) }

    val modelInstance = remember { mutableListOf<ModelInstance>() }
    val trackingFailure = remember {
        mutableStateOf<TrackingFailureReason?>(null)
    }

    val frame = remember {
        mutableStateOf<Frame?>(null)
    }



    //SCENE VIEW...
    ARScene(
        modifier = Modifier.fillMaxSize(),
        engine = engine,
        view = view,
        childNodes = childNodes,
        modelLoader = modelLoader,
        materialLoader = materialLoader,
        collisionSystem = collision,
        planeRenderer = planeRenderer.value,
        cameraNode = cameraNode,
        onTrackingFailureChanged = {
            trackingFailure.value = it
        },
        onSessionUpdated =  { _, updatedFrame->
            frame.value = updatedFrame
        },
        sessionConfiguration = { session, config ->
            config.depthMode = when(session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                true -> Config.DepthMode.AUTOMATIC
                else -> Config.DepthMode.DISABLED
            }
            config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
        },
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed =  { motionEvent, node->

                if (node==null) { //null means mean the place is empty
                   //if the area is plane and no item there then we have to plane our model by setting the anchor...
                   //so on confirming the place for model by tapping on screen this will make an anchor and place the model over there like a anchored ship in an ocean
                    val hitTestResult = frame.value?.hitTest(motionEvent.x,motionEvent.y)
                    hitTestResult?.firstOrNull {
                        it.isValid(
                            depthPoint = false,
                            point = false
                        )
                    }?.createAnchorOrNull()?.let { anchor->
                        //create the anchor and place the model
                        val nodeModel = createAnchorNode(
                            engine = engine,
                            modelLoader = modelLoader,
                            materialLoader = materialLoader,
                            modelInstance = modelInstance,
                            anchor = anchor,
                            model = "models/car.glb"
                        )
                        childNodes += nodeModel
                    }

                } //else case means there is an itm

            }
        )
    )



}

@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun createAnchorNode(
    engine: Engine,
    modelLoader: ModelLoader,
    materialLoader: MaterialLoader,
    modelInstance: MutableList<ModelInstance>,
    anchor: Anchor,
    model: String
    ) : AnchorNode{

    val anchorNode = AnchorNode(engine, anchor)
    val modelNode = ModelNode(
        modelInstance = modelInstance.apply {
            if (isEmpty()) {
                this+=modelLoader.createInstancedModel(model, 10)
            }
        }.removeLast(),
        scaleToUnits = 0.3f,

    ).apply {
        isEditable = true //to make the node editable
    }

    val bounds = CubeNode(
        engine = engine,
        size = modelNode.extents,
        center = modelNode.center,
        materialInstance = materialLoader.createColorInstance(Color.White)
    ).apply { isVisible = false }

    modelNode.addChildNode(bounds)
    anchorNode.addChildNode(modelNode)

    listOf(modelNode, anchorNode).forEach {
        it.onEditingChanged = { editingTransform->
            bounds.isVisible = editingTransform.isNotEmpty()
        }
    }

    return anchorNode

}