package com.example.kusukamoto.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.kusukamoto.R

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userName: String = "",
    email: String = "",
    phoneNumber: String = "",
    onSaveClick: () -> Unit = {},
    onMyCarsClick: () -> Unit = {},
    onUpdatePasswordClick: () -> Unit = {}
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Lançador de atividade para escolher uma imagem da galeria
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture Section (Editable)
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .clickable { launcher.launch("image/*") }, // Abre a galeria para escolher imagem
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImageUri != null) {
                        // Mostra a imagem selecionada
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImageUri),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        // Mostra a imagem padrão
                        Image(
                            painter = painterResource(id = R.drawable.usericon),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    // Adiciona uma sobreposição preta com opacidade de 50% e um ícone de lápis
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)) // Fundo preto com 50% de opacidade
                            .align(Alignment.Center)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit, // Ícone de lápis
                            contentDescription = "Edit Profile Picture",
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(24.dp) // Tamanho do ícone
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Name Field
                OutlinedTextField(
                    value = userName,
                    onValueChange = { /* Handle name input */ },
                    label = { Text(text = "Nome") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Email Field
                OutlinedTextField(
                    value = email,
                    onValueChange = { /* Handle email input */ },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Phone Number Field
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { /* Handle phone number input */ },
                    label = { Text(text = "Número de telefone") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Opção: Meus Carros
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMyCarsClick() }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cars),
                        contentDescription = "Meus Carros",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Meus carros",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF00E0C6)
                    )
                }

                // Opção: Atualizar Senha
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onUpdatePasswordClick() }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = "Atualizar Senha",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Atualizar password",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "Arrow",
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF00E0C6)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botão de salvar no rodapé
                Button(
                    onClick = onSaveClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00E0C6)
                    ),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(
                        text = "Salvar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    )
}
