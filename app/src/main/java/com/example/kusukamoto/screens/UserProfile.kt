import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.rememberNavController
import com.example.kusukamoto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    userName: String = "Murade Lobo",
    email: String = "muradelobo@gmail.com",
    onAccountClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            // Top App Bar with Back Arrow
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White // Set the color of the back arrow icon
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF00E0C6), // Background color (teal)
                    titleContentColor = Color.White, // Title color
                    navigationIconContentColor = Color.White // Navigation icon color
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White) // Set the background color to white
                    .padding(paddingValues)
            ) {
                // Top section with profile picture and user info
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF00E0C6)) // Teal background for profile section
                        .padding(vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Picture
                    Image(
                        painter = painterResource(id = R.drawable.usericon),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )

                    // User Name
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )

                    // Email
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color.White
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Options (Minha Conta, Editar Perfil, Sair)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // "Minha Conta" Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onAccountClick() }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Minha Conta",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Set icon color to black
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Minha conta",
                            fontSize = 18.sp,
                            color = Color.Black // Set text color to black
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    // "Editar Perfil" Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onEditProfileClick() }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Editar Perfil",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Set icon color to black
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Editar perfil",
                            fontSize = 18.sp,
                            color = Color.Black // Set text color to black
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    // "Sair" Section
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onLogoutClick() }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExitToApp,
                            contentDescription = "Sair",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black // Set icon color to black
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Sair",
                            fontSize = 18.sp,
                            color = Color.Black // Set text color to black
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen(navController = rememberNavController())
}
