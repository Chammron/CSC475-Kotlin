import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Recipe(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val ingredients: List<String>,
    val instructions: String,
    var isFavorite: Boolean = false
) {
    var isFavoriteState by mutableStateOf(isFavorite)
}