/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.horologist.datalayer.sample

import com.google.android.horologist.datalayer.sample.screens.info.infoScreenRoute
import com.google.android.horologist.datalayer.sample.screens.nodesactions.nodeDetailsScreenRoute


sealed class Screen(
    val route: String,
) {
    data object MainScreen : Screen("mainScreen")

    data object MotionDetectorScreen : Screen("motionDetectorScreen")

    data object CounterScreen : Screen("counterScreen")
    data object ListNodesScreen : Screen("listNodesScreen")
    data object StepsScreen : Screen("stepsScreen")

    data object HeartRateScreen : Screen("heartRateScreen")

    data object AppHelperTrackingScreen : Screen("appHelperTrackingScreen")
    data object AppHelperNodesActionsScreen : Screen("appHelperNodesActionsScreen")
    data object AppHelperNodesListenerScreen : Screen("appHelperNodesListenerScreen")

    data object AppHelperNodeDetailsScreen : Screen(nodeDetailsScreenRoute)

    data object InfoScreen : Screen(infoScreenRoute)

    data object TabContainerScreen : Screen("tabContainerScreen")
}
