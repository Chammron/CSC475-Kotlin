package com.cameron.recipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecipeDetailScreen(recipe: Recipe, navController: NavController) {
    //Main vertical layout for the detail screen.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        //Back button to go back to the previous screen.
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_arrow_back_24), // Back arrow icon
                contentDescription = "Back"
            )
        }

        //Displays the recipe image.
        Image(
            painter = painterResource(id = recipe.imageResId),
            contentDescription = recipe.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Displays the recipe name.
        Text(
            text = recipe.name,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Section header for ingredients.
        Text(
            text = "Ingredients:",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        //List of ingredients.
        recipe.ingredients.forEach { ingredient ->
            Text(text = "- $ingredient", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        //Section header for instructions.
        Text(
            text = "Instructions:",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        //Cooking instructions.
        Text(
            text = recipe.instructions,
            fontSize = 16.sp
        )
    }
}