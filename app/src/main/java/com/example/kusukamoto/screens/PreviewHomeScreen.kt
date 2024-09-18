import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    // Cria um NavController para o preview
    val navController = rememberNavController()

    // Passa o NavController para a HomeScreen
    HomeScreen(navController = navController)
}