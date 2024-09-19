package ggn.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
    val scrollState = rememberScrollState()
    val uriHandler = LocalUriHandler.current
    var showSkills by remember { mutableStateOf(false) }
    var showProjects by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Header
        Image(
            painter = painterResource(Res.drawable.my_pic), // Replace with your image
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .border(4.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text("Gagandeep Singh", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Android Developer", fontSize = 20.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(32.dp))

        // About Me
        SectionCard(title = "About Me") {
            Text(
                "Passionate Android developer with 10 years of experience. " +
                        "Experienced in Kotlin, Jetpack Compose, and building high-quality mobile applications. " +
                        "Always eager to learn new technologies and contribute to innovative projects.",
                fontSize = 16.sp
            )
        }

        // Skills
        ExpandableSectionCard(
            title = "Skills",
            isExpanded = showSkills,
            onExpandChange = { showSkills = it }
        ) {
            SkillsList()
        }

        // Projects
        ExpandableSectionCard(
            title = "Projects",
            isExpanded = showProjects,
            onExpandChange = { showProjects = it }
        ) {
            Column {
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

        // Contact
        SectionCard(title = "Contact") {
            Text(
                "gagandeep.singh@smartdatainc.net",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier.clickable {
                    uriHandler.openUri("mailto:gagandeep.singh@smartdatainc.net")
                }
            )
        }

        Spacer(modifier = Modifier.height(48.dp))
    }
}

@Composable
fun SectionCard(title: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

@Composable
fun ExpandableSectionCard(
    title: String,
    isExpanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onExpandChange(!isExpanded) },
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .clickable { onExpandChange(!isExpanded) }
            )
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))
                content()
            }
        }
    }
}

@Composable
fun ProjectItem(title: String, description: String) {
    Column {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(description, fontSize = 14.sp)
    }
}

@Composable
fun SkillsList() {
    Column(modifier = Modifier.padding(16.dp)) {
        SkillCategory(title = "Languages") {
            SkillItem("Kotlin (primary)")
            SkillItem("Java")
        }
        SkillCategory(title = "Frameworks & Libraries") {
            SkillItem("Jetpack Compose")
            SkillItem("Android SDK")
            SkillItem("Jetpack libraries")
            SkillItem("Coroutines")
            SkillItem("Dependency injection")
            SkillItem("Networking libraries")
            SkillItem("Testing frameworks")
        }
        // ... other skill categories
    }
}

@Composable
fun SkillCategory(title: String, content: @Composable () -> Unit) {
    Column {
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        content()
    }
}

@Composable
fun SkillItem(text: String) {
    var showDetails by remember { mutableStateOf(false) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDetails = !showDetails }
                .padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text, fontSize = 16.sp)
        }
        AnimatedVisibility(
            visible = showDetails,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            // Show skill details here (e.g., proficiency level, experience)
            Text(" - Some details about this skill", fontSize = 14.sp, color = Color.Gray)
        }
        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}