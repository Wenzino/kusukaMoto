import androidx.compose.runtime.*
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.kusukamoto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = AuthViewModel(),
    onAccountClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {viewModel.logout(navController)}
) {
    // Estado para armazenar os dados do usuário
    var userData by remember { mutableStateOf<AuthViewModel.UserData?>(null) }

    // Carregar dados do Firestore ao iniciar a tela
    LaunchedEffect(Unit) {
        viewModel.getUserDataFromFirestore { data ->
            userData = data
        }
    }

    // Exibir a tela de perfil
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
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
                userData?.let { data ->
                    // Exibir as informações do usuário do Firestore
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF00E0C6)) // Teal background for profile section
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Foto do usuário
                        Image(
                            painter = rememberAsyncImagePainter(data.photoUrl), // Carrega a URL da foto
                            contentDescription = "Profile Picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                        )

                        // Nome do usuário
                        Text(
                            text = data.name,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = Color.White
                            )
                        )

                        // Email do usuário
                        Text(
                            text = data.email,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.White
                            )
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Opções (Minha Conta, Editar Perfil, Sair)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        // Opção "Minha Conta"
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
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Minha conta",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Opção "Editar Perfil"
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
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Editar perfil",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Opção "Sair" que chama a função de logout
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onLogoutClick() }  // Chama a função de logout
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ExitToApp,
                                contentDescription = "Sair",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                text = "Sair",
                                fontSize = 18.sp,
                                color = Color.Black
                            )
                        }
                    }
                } ?: run {
                    // Caso os dados ainda não tenham sido carregados ou haja erro
                    Text("Carregando informações do usuário...")
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
