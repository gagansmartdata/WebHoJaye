package ggn.example.project

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import webhojaye.composeapp.generated.resources.Res
import webhojaye.composeapp.generated.resources.compose_multiplatform
import webhojaye.composeapp.generated.resources.my_pic

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DeveloperProfileScreen()
            }
        }
    }

}

@Composable
fun DeveloperProfileScreen() {
    var showSkills by remember { mutableStateOf(false) }
    var showProjects by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(Res.drawable.my_pic),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("Gagandeep Singh", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Android Developer", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(24.dp))

                // Bio
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    backgroundColor = Color.White,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Bio", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Passionate Android developer with 10 years of experience. " +
                                    "Experienced in Kotlin, Jetpack Compose, and building high-quality mobile applications. " +
                                    "Always eager to learn new technologies and contribute to innovative projects."
                        )
                    }
                }

                // Skills
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { showSkills = !showSkills }.indication(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ),
                    backgroundColor = Color.White,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Skills", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        AnimatedVisibility(
                            showSkills, enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically(),
                        ) {
                            if (showSkills) {
                                Spacer(modifier = Modifier.height(8.dp))
                                SkillsList()
                            }
                        }
                    }
                }


                // Projects
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { showProjects = !showProjects },
                    backgroundColor = Color.White,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text("Projects", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    AnimatedVisibility(
                        showProjects, enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically(),
                    ) {
                        if (showProjects) {

                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                            ) {
                                Spacer(modifier = Modifier.height(8.dp))
                                // Add your project composables here
                                ProjectItem(
                                    title = "Project 1",
                                    description = "Description of Project 1"
                                )
                                ProjectItem(
                                    title = "Project 2",
                                    description = "Description of Project 2"
                                )
                            }
                        }
                    }
                }

                // Contact Information
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    backgroundColor = Color.White,
                    elevation = 8.dp,
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Contact", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "gagandeep.singh@smartdatainc.net",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                uriHandler.openUri("mailto:gagandeep.singh@smartdatainc.net")
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun ProjectItem(title: String, description: String) {
    Column {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(description)
    }
}


@Composable
fun SkillsList() {
    Column(modifier = Modifier.padding(16.dp)) {
        SkillCategory(title = "Languages") {
            SkillItem("Kotlin (primary)")
            SkillItem("Java (for legacy projects)")
        }
        SkillCategory(title = "Frameworks & Libraries") {
            SkillItem("Jetpack Compose (modern UI toolkit)")
            SkillItem("Android SDK")
            SkillItem("Jetpack libraries (e.g., Room, ViewModel, LiveData, Navigation)")
            SkillItem("Coroutines (for asynchronous programming)")
            SkillItem("Dependency injection (e.g., Hilt, Koin)")
            SkillItem("Networking libraries (e.g., Retrofit, OkHttp)")
            SkillItem("Testing frameworks (e.g., JUnit, Mockito, Espresso)")
        }
        SkillCategory(title = "Architecture") {
            SkillItem("MVVM (Model-View-ViewModel)")
            SkillItem("MVI (Model-View-Intent)")
        }
        SkillCategory(title = "Tools") {
            SkillItem("Android Studio")
            SkillItem("Gradle (build system)")
            SkillItem("Version control systems (e.g., Git)")
            SkillItem("Profiling and debugging tools")
        }
        SkillCategory(title = "Kotlin Multiplatform (KMP)") {
            SkillItem("Kotlin Multiplatform libraries:")
            SkillItem("- Coroutines", isSubItem = true)
            SkillItem("- Serialization", isSubItem = true)
            SkillItem("- Ktor (for networking)", isSubItem = true)
            SkillItem("- SQLDelight (for database access)", isSubItem = true)
            SkillItem("Understanding of platform-specific implementations")
            SkillItem("Experience with KMP project setup and configuration")
        }
        SkillCategory(title = "General Software Development") {
            SkillItem("Object-oriented programming (OOP) principles")
            SkillItem("Design patterns")
            SkillItem("Clean code principles")
            SkillItem("Agile development methodologies")
            SkillItem("Problem-solving and debugging skills")
            SkillItem("Good communication and collaboration skills")
        }
    }
}

@Composable
fun SkillCategory(title: String, content: @Composable () -> Unit) {
    Column {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        content()
    }
}

@Composable
fun SkillItem(text: String, isSubItem: Boolean = false) {
    val modifier = if (isSubItem) Modifier.padding(start = 16.dp) else Modifier
    Text("• $text", modifier = modifier)
}