import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.kusukamoto.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = Color(0xFF00E0C6),
        contentColor = Color.White
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    modifier = Modifier.size(24.dp), // Diminuir o tamanho do ícone
                    tint = Color(0xFF142D55) // Cor do ícone quando não selecionado
                )
            },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00E0C6), // Cor do ícone quando selecionado
                unselectedIconColor = Color(0xFF142D55), // Cor do ícone quando não selecionado
                selectedTextColor = Color(0xFF00E0C6), // Cor do texto quando selecionado
                unselectedTextColor = Color.Gray // Cor do texto quando não selecionado
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.history),
                    contentDescription = "Historico",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF142D55)
                )
            },
            label = { Text("Historico") },
            selected = false,
            onClick = { navController.navigate("historico") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00E0C6),
                unselectedIconColor = Color(0xFF142D55),
                selectedTextColor = Color(0xFF00E0C6),
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.cars),
                    contentDescription = "Carros",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF142D55)
                )
            },
            label = { Text("Carros") },
            selected = false,
            onClick = { navController.navigate("carros") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00E0C6),
                unselectedIconColor = Color(0xFF142D55),
                selectedTextColor = Color(0xFF00E0C6),
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Perfil",
                    modifier = Modifier.size(24.dp),
                    tint = Color(0xFF142D55)
                )
            },
            label = { Text("Perfil") },
            selected = false,
            onClick = { navController.navigate("userProfile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF00E0C6),
                unselectedIconColor = Color(0xFF142D55),
                selectedTextColor = Color(0xFF00E0C6),
                unselectedTextColor = Color.Gray
            )
        )
    }
}
