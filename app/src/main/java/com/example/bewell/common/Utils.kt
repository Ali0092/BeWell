package com.example.bewell.common

import com.example.bewell.R
import com.example.bewell.domain.model.UserProfile

object Utils {

    var selectedExercise: FitnessExercises = FitnessExercises()

    fun checkIfCanMoveToNext(screen: Int = 0,userData: UserProfile, canMove: (Boolean, String) -> Unit) {

        if (screen==0) {
            if (userData.name == "") {
                canMove(false, "Please enter your name")
            }
            if (userData.age == 0) {
                canMove(false, "Please enter your age")
            }
            if (userData.gender == "") {
                canMove(false, "Please enter your gender")
            }
            if (userData.height == 0.0) {
                canMove(false, "Please enter your Height")
            }
            if (userData.weight == 0.0) {
                canMove(false, "Please enter your weight")
            }else {
                canMove(true, "")
            }
        } else {
            if (userData.stepsGoal == 0) {
                canMove(false, "Please enter your daily steps goal")
            }
            if (userData.caloriesIntake == 0) {
                canMove(false, "Please enter your calories intake")
            }
            if (userData.caloriesBurnedTarget == 0) {
                canMove(false, "Please enter your calories burned")
            }
            if (userData.waterIntake == 0) {
                canMove(false, "Please enter your daily water intake")
            }
            if (userData.sleepTime == 0) {
                canMove(false, "Please enter your sleep time")
            }else {
                canMove(true, "")
            }
        }

    }

    val bottomNavigationItems = listOf(
        BottomNavItems(Screens.HOME.name, "Home", R.drawable.home_icon),
        BottomNavItems(Screens.FITNESS.name, "Fitness", R.drawable.fitness_icon),
        BottomNavItems(Screens.PROFILE.name, "Profile", R.drawable.profile_icon),
    )

    val weightLossExercises  = listOf(
        FitnessExercises(id = 1, name = "Jumping Jacks", image =  R.drawable.wl_10, description = "Start standing with arms at sides.\n" + "Jump while spreading your legs and swinging arms overhead.\n" + "Return to starting position.\n" + "Repeat continuously at a steady pace.\n" + "Great for warming up and boosting heart rate.\n" + "Targets legs, shoulders, and cardiovascular endurance.\n" + "Do 3 sets of 45 seconds with 15s rest.\n" + "Can be done anywhere with no equipment.\n" + "Helps activate full body.\n" + "Modify intensity by slowing or speeding pace.\n" + "Burn approx. 100 calories per 10 mins.\n" + "Excellent for morning energy burst."),
        FitnessExercises(1, "Burpees", R.drawable.wl_9, description = "Stand upright, then drop into a squat with hands on floor.\n" + "Kick your feet back to a push-up position.\n" + "Do one push-up (optional), then jump back to squat.\n" + "Explosively jump up reaching arms overhead.\n" + "One of the best full-body fat-burning exercises.\n" + "Works chest, arms, quads, glutes, and core.\n" + "Start with 10 reps × 3 sets.\n" + "Rest 30s between sets.\n" + "Use mat to soften the landing.\n" + "Scale down by skipping the push-up.\n" + "Burn 10–15 calories per minute.\n" + "Builds endurance and power."),
        FitnessExercises(1, "Mountain Climbers", R.drawable.wl_8, description = "Get into a push-up position.\n" + "Quickly drive one knee toward your chest.\n" + "Alternate legs in a running motion.\n" + "Maintain tight core throughout.\n" + "Boosts cardio and torches belly fat.\n" + "Work in 30s bursts, 3–4 sets.\n" + "Keep hips low, back straight.\n" + "Ideal as part of HIIT circuit.\n" + "Engages abs, shoulders, and legs.\n" + "Burn up to 100 calories in 10 mins.\n" + "Try variations like slow climbers or cross-body."),
        FitnessExercises(1, "High Knees\n", R.drawable.wl_7, description = "Stand tall with feet hip-width apart.\n" + "Run in place by lifting knees as high as possible.\n" + "Pump arms in sync to maintain rhythm.\n" + "Keep your core tight and land softly.\n" + "Aim for 30–45 seconds per round.\n" + "Complete 3–4 sets with 20s rest.\n" + "Improves heart rate and leg explosiveness.\n" + "Great for core activation too.\n" + "Works calves, quads, glutes, and abs.\n" + "A solid cardio finisher or warm-up.\n" + "Burn around 100–140 calories in 10 minutes."),
        FitnessExercises(1, "Jump Rope\n", R.drawable.wl_6, description = "Hold the rope handles firmly at your sides.\n" + "Swing the rope from behind over your head.\n" + "Jump just enough to clear the rope.\n" + "Maintain a steady pace, breathing rhythmically.\n" + "Try single jumps or double-unders as you progress.\n" + "1–2 mins per round, 3–5 rounds.\n" + "Works calves, shoulders, and cardiovascular system.\n" + "Improves coordination and endurance.\n" + "Very efficient: 10 mins can burn over 130 calories.\n" + "Light, portable, and fun."),
        FitnessExercises(1, "Skaters", R.drawable.wl_5, description = "Begin standing, then leap to your right.\n" + "Land on your right foot while swinging the left behind.\n" + "Jump to your left and repeat the motion.\n" + "Keep your chest up and land soft.\n" + "Mimics skating motion — works glutes and outer thighs.\n" + "Do 30–45 seconds × 3–4 sets.\n" + "Maintain speed but control each jump.\n" + "Boosts lateral mobility and stability.\n" + "Engages legs, hips, and core.\n" + "One of the best cardio + agility combos."),
        FitnessExercises(1, "Running (Outdoor or Treadmill)\n", R.drawable.wl_4, description = "Start with a 5-minute brisk walk to warm up.\n" + "Increase pace to a steady jog or run.\n" + "Keep back straight, arms relaxed.\n" + "Breathe in through nose, out through mouth.\n" + "Run for 20–30 minutes or do intervals (1 min fast / 1 min slow).\n" + "Works legs, lungs, heart, and core.\n" + "Burn 200–400 calories depending on intensity.\n" + "Improves endurance and mood (runner’s high).\n" + "Perfect for both beginners and pros.\n" + "Track distance with your app for progress."),
        FitnessExercises(1, "Jump Squats\n", R.drawable.wl_3, description = "Stand with feet shoulder-width apart.\n" + "Lower into a squat, keeping knees behind toes.\n" + "Explosively jump up, swinging arms.\n" + "Land softly and lower into next squat.\n" + "Works quads, glutes, hamstrings, and calves.\n" + "Do 10–12 reps × 3 sets.\n" + "Rest 30 seconds between sets.\n" + "Increases leg strength and power.\n" + "Great HIIT finisher to burn fat fast.\n" + "Avoid if you have knee issues."),
        FitnessExercises(1, "Fast-Paced Walking\n", R.drawable.wl_2, description = "Start with brisk pace — not a stroll.\n" + "Keep shoulders back, arms swinging naturally.\n" + "Walk for 20–30 minutes daily.\n" + "Burn up to 150–200 calories per session.\n" + "Easier on joints than running.\n" + "Improves mood, circulation, and digestion.\n" + "Track steps and distance with pedometer.\n" + "Listen to music or podcasts for extra motivation.\n" + "Great low-impact option for all ages."),
        FitnessExercises(1, "Bicycle Crunches\n", R.drawable.wl_1, description = "Lie flat on back with hands behind head.\n" + "Lift legs and shoulder blades off the floor.\n" + "Bring right elbow toward left knee and switch.\n" + "Continue pedaling motion without pulling neck.\n" + "Works obliques, upper and lower abs.\n" + "Do 15–20 reps each side × 3 sets.\n" + "Go slow and controlled for better burn.\n" + "Burn around 50–100 calories in 10 mins.\n" + "Core tight = better posture and fat loss."),
    )

    val muscleBuildExercises  = listOf(
        FitnessExercises(1, "Push-ups", R.drawable.mb_10, description = "Start in a plank position, hands shoulder-width apart.\n" + "Keep your body in a straight line from head to heels.\n" + "Lower chest to the floor by bending elbows.\n" + "Push back up without locking elbows.\n" + "Focus on engaging chest, triceps, and core.\n" + "Do 10–15 reps × 3 sets (modify by doing on knees if needed).\n" + "Exhale when pushing up, inhale on the way down.\n" + "Builds upper body strength and stability.\n" + "Can vary (wide, close, diamond) for different muscle groups.\n" + "A timeless, equipment-free classic for chest and arms.\n" + "Great to include in warm-up or burnout sets."),
        FitnessExercises(1, "Pull-ups", R.drawable.mb_9, description = "Grab a pull-up bar with overhand grip, hands shoulder-width apart.\n" + "Start from a dead hang, arms fully extended.\n" + "Pull your chest up to the bar by squeezing shoulder blades.\n" + "Lower yourself in a controlled manner.\n" + "Targets back, biceps, shoulders, and core.\n" + "Start with 3–5 reps × 3 sets (use resistance band if needed).\n" + "Avoid swinging — strict form > more reps.\n" + "Builds real-world upper body pulling strength.\n" + "Harder than it looks — a long-term benchmark move.\n" + "Consider chin-ups for more biceps activation."),
        FitnessExercises(1, "Squats (Bodyweight/Weighted)", R.drawable.mb_8, description = "Stand with feet shoulder-width apart.\n" + "Lower your hips back and down as if sitting in a chair.\n" + "Keep knees behind toes and back straight.\n" + "Go as low as you can comfortably.\n" + "Push through heels to stand back up.\n" + "Do 12–15 reps × 3 sets.\n" + "Engage glutes, quads, hamstrings, and core.\n" + "Use dumbbells or a barbell for added resistance.\n" + "Builds leg power, posture, and balance.\n" + "Key functional movement for all body types."),
        FitnessExercises(1, "Deadlifts", R.drawable.mb_7, description = "Stand with feet under barbell or dumbbells.\n" + "Hinge at the hips and bend knees to grip weights.\n" + "Keep chest up and back straight.\n" + "Drive through heels to lift weight up, locking hips at top.\n" + "Lower with control, maintaining form.\n" + "Do 8–10 reps × 3 sets.\n" + "Targets glutes, hamstrings, back, and core.\n" + "Best for posterior chain strength and stability.\n" + "Requires proper technique to avoid injury.\n" + "A compound powerhouse exercise."),
        FitnessExercises(1, "Bench Press", R.drawable.mb_6, description = "Lie flat on a bench with barbell or dumbbells.\n" + "Grip bar slightly wider than shoulder-width.\n" + "Lower weight to mid-chest slowly.\n" + "Press up explosively without locking elbows.\n" + "Inhale down, exhale up.\n" + "Do 8–12 reps × 3–4 sets.\n" + "Works chest, triceps, shoulders.\n" + "Spotter recommended for heavy sets.\n" + "Best for upper-body pushing strength.\n" + "Adjust incline/decline to hit chest from different angles."),
        FitnessExercises(1, "Shoulder Press", R.drawable.mb_5, description = "Sit or stand holding dumbbells at shoulder height.\n" + "Press weights upward until arms are fully extended.\n" + "Lower under control to starting position.\n" + "Do 10–12 reps × 3 sets.\n" + "Keep spine neutral and avoid flaring elbows.\n" + "Builds deltoids, triceps, and upper back.\n" + "One of the best exercises for broader shoulders.\n" + "Can be done with barbell or resistance bands too."),
        FitnessExercises(1, "Bent-over Rows", R.drawable.mb_4, description = "Stand with knees slightly bent, hinge at hips.\n" + "Hold dumbbells or barbell with arms extended downward.\n" + "Pull weights toward your waist, squeezing shoulder blades.\n" + "Lower under control and repeat.\n" + "Do 10–12 reps × 3 sets.\n" + "Targets back, rear delts, and biceps.\n" + "Maintain flat back throughout — avoid rounding.\n" + "Great for posture and upper back definition.\n" + "Best done slow and controlled."),
        FitnessExercises(1, "Bicep Curls", R.drawable.mb_3, description = "Hold dumbbells with palms facing forward.\n" + "Keep elbows tucked close to sides.\n" + "Curl weights up toward shoulders.\n" + "Lower slowly to full arm extension.\n" + "Do 10–15 reps × 3 sets.\n" + "Avoid swinging — strict form maximizes gains.\n" + "Variations: hammer curls, barbell curls.\n" + "Focuses purely on biceps hypertrophy.\n" + "Great pump when done slowly."),
        FitnessExercises(1, "Tricep Dips", R.drawable.mb_2, description = "Sit on a bench or chair edge with hands beside hips.\n" + "Slide forward so hips are off the edge.\n" + "Bend elbows to lower body down, then press up.\n" + "Do 10–12 reps × 3 sets.\n" + "Targets triceps, chest, and shoulders.\n" + "Keep back close to bench throughout.\n" + "Can straighten legs for more resistance.\n" + "Simple but highly effective for arm strength."),
        FitnessExercises(1, "Plank-to-Push-up", R.drawable.mb_1, description = "Start in forearm plank position.\n" + "Push up one arm at a time into push-up position.\n" + "Lower back into plank one arm at a time.\n" + "Keep hips steady, core tight.\n" + "Do 30 seconds × 3–4 rounds.\n" + "Engages chest, shoulders, triceps, and core.\n" + "Builds endurance, stability, and coordination.\n" + "Great HIIT and functional training finisher."),
    )

    val balancedTrainingExercises  = listOf(
        FitnessExercises(1, "Plank", R.drawable.bt_10, description = "Start on forearms and toes, keeping your body straight.\n" + "Elbows under shoulders, gaze down, spine neutral.\n" + "Engage core, squeeze glutes, and avoid hip sagging.\n" + "Hold for 30–60 seconds × 3–4 rounds.\n" + "Builds core strength and endurance.\n" + "Improves posture and spinal stability.\n" + "Activates shoulders, abs, glutes, and lower back.\n" + "Progress to side planks or plank with leg lifts.\n" + "A foundational exercise for all fitness levels.\n" + "Static but very challenging and effective."),
        FitnessExercises(1, "Lunges", R.drawable.bt_9, description = "Stand tall and step one leg forward.\n" + "Lower your body until both knees form 90° angles.\n" + "Push back to starting position and switch sides.\n" + "Do 10–12 reps per leg × 3 sets.\n" + "Builds leg strength, balance, and coordination.\n" + "Works quads, hamstrings, glutes, and calves.\n" + "Keep upper body upright and avoid leaning forward.\n" + "Can hold dumbbells for added resistance.\n" + "Improves single-leg stability for athletic movement."),
        FitnessExercises(1, "Glute Bridges", R.drawable.bt_8, description = "Lie on back, knees bent, feet flat on floor.\n" + "Hands by sides, press heels to lift hips.\n" + "Squeeze glutes at the top and lower slowly.\n" + "Do 15 reps × 3–4 sets.\n" + "Targets glutes, hamstrings, and core.\n" + "Great for relieving lower back tightness.\n" + "Can elevate feet or add weights for intensity.\n" + "Improves hip mobility and posture.\n" + "Perfect warm-up or finisher for leg days."),
        FitnessExercises(1, "Russian Twists", R.drawable.bt_7, description = "Sit on the floor, knees bent, feet slightly lifted.\n" + "Hold hands or weight in front of chest.\n" + "Twist torso side to side, tapping the floor each time.\n" + "Keep core tight and move slowly.\n" + "Do 20 twists (10 each side) × 3 sets.\n" + "Targets obliques and deep core stabilizers.\n" + "Can hold a medicine ball or dumbbell.\n" + "Improves rotation control and core definition.\n" + "Great seated ab exercise with minimal space needed."),
        FitnessExercises(1, "Wall Sit", R.drawable.bt_6, description = "Stand with back against a wall.\n" + "Slide down until thighs are parallel to the floor.\n" + "Hold for 30–60 seconds × 3 rounds.\n" + "Keep knees above ankles, back flat.\n" + "Works quads, glutes, hamstrings, and calves.\n" + "Mental and muscular endurance challenge.\n" + "Increases lower body stamina.\n" + "Great low-impact strength builder."),
        FitnessExercises(1, "Yoga: Downward Dog", R.drawable.bt_5, description = "Start in a high plank, then lift hips toward ceiling.\n" + "Form an inverted V shape.\n" + "Keep hands shoulder-width and feet hip-width apart.\n" + "Press heels toward the floor and relax neck.\n" + "Hold for 30 seconds × 3 rounds.\n" + "Stretches hamstrings, calves, back, and shoulders.\n" + "Builds upper body endurance.\n" + "Excellent for recovery and flexibility.\n" + "Calms nervous system and improves circulation."),
        FitnessExercises(1, "Bird Dog", R.drawable.bt_4, description = "Start on all fours (hands under shoulders, knees under hips).\n" + "Extend opposite arm and leg straight out.\n" + "Hold for 2–3 seconds and switch sides.\n" + "Do 10–12 reps per side × 3 sets.\n" + "Enhances core balance and coordination.\n" + "Engages lower back, abs, glutes, and shoulders.\n" + "Great for spine stabilization.\n" + "Simple but powerful full-body movement."),
        FitnessExercises(1, "Superman Hold", R.drawable.bt_3, description = "Lie face down with arms extended in front.\n" + "Lift arms, chest, and legs off the floor.\n" + "Hold for 10–15 seconds, then relax.\n" + "Do 3–4 rounds of 3–5 reps.\n" + "Targets lower back, glutes, hamstrings, and shoulders.\n" + "Improves posture and spinal control.\n" + "Can alternate arms/legs for variation.\n" + "Safe and effective for strengthening posterior chain."),
        FitnessExercises(1, "Dumbbell Squat-to-Press", R.drawable.bt_2, description = "Hold dumbbells at shoulder height.\n" + "Perform a squat, keeping chest up and knees behind toes.\n" + "As you stand, press weights overhead.\n" + "Lower weights back to shoulders and repeat.\n" + "Do 10–12 reps × 3 sets.\n" + "Full-body compound movement.\n" + "Builds leg and shoulder strength in one go.\n" + "Boosts coordination and cardiovascular endurance.\n" + "Perfect for time-efficient workouts."),
        FitnessExercises(1, "Side Plank", R.drawable.bt_1, description = "Lie on your side with elbow under shoulder.\n" + "Lift hips to form a straight line from head to heels.\n" + "Hold for 30 seconds per side × 3 sets.\n" + "Engage obliques, glutes, and deep core.\n" + "Builds stability and side body strength.\n" + "Can raise top leg for extra challenge.\n" + "Helps with balance and posture.\n" + "A strong core = better performance in all areas."),
    )


}


data class BottomNavItems(
    val route: String,
    val title: String ,
    val icon: Int = 0
)

data class FitnessExercises(
    val id: Int = 0,
    val name: String = "",
    val image: Int = 0,
    val description: String = ""
)


