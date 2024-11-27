package com.cameron.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable

@Composable
fun RecipeCard(
    recipe: Recipe,
    onRecipeClick: () -> Unit,
    onFavoriteToggle: () -> Unit
) {
    // Card to display the recipe
    Card(
        //Styling for the card.
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onRecipeClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        //Layout for the card.
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Displays the recipe image.
            Image(
                painter = painterResource(id = recipe.imageResId),
                contentDescription = "Recipe Image",
                //Modifiers for the image.
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            //Space between image and recipe name.
            Spacer(modifier = Modifier.width(16.dp))
            //Column for recipe name and favorite status.
            Column(
                modifier = Modifier.weight(1f)
            ) {
                //Recipe name + styling.
                Text(
                    text = recipe.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                //Text to display whether the recipe is favorited.
                Text(
                    text = if (recipe.isFavorite) "Favorited" else "Not Favorited",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
            //Favorite icon toggle button.
            IconButton(onClick = { onFavoriteToggle() }) {
                //Icon based on the recipe's favorite status.
                val icon = if (recipe.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_outline
                Icon(
                    painter = painterResource(id = icon),
                    //Accessibility description for the icon.
                    contentDescription = "Favorite Toggle"
                )
            }
        }
    }
}