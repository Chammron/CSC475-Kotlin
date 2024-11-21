package com.cameron.recipeapp

object RecipeData {
    val recipes = mutableListOf(
        Recipe(
            id = 1,
            name = "Pancakes",
            imageResId = R.drawable.pancake, // Replace with the actual image resource
            ingredients = listOf(
                "1 cup all-purpose flour",
                "2 tbsp sugar",
                "1 tsp baking powder",
                "1 egg",
                "1 cup milk",
                "2 tbsp melted butter"
            ),
            instructions = "1. Mix dry ingredients.\n2. Add wet ingredients and mix until smooth.\n3. Cook on a greased pan until golden brown.",
            isFavorite = false
        ),
        Recipe(
            id = 2,
            name = "Root Beer Float",
            imageResId = R.drawable.rootbeerfloat,
            ingredients = listOf(
                "Vanilla ice cream",
                "Root beer",
            ),
            instructions = "1. Spoon a scoop or two of vanilla ice cream into a tall glass. Slowly pour root beer into the glass, allowing the foam to rise and then recede before adding more root beer.\n2. Serve with straws and spoons.",
            isFavorite = false
        ),
        Recipe(
            id = 3,
            name = "Deviled Eggs",
            imageResId = R.drawable.deviledeggs,
            ingredients = listOf(
                "6 large eggs",
                "3 tablespoons mayonnaise",
                "1 teaspoon Dijon mustard",
                "1 teaspoon apple cider vinegar",
                "Salt and pepper",
                "paprika"
            ),
            instructions = "1. Bring a pot of water to a boil. Reduce the heat to low (or off) to ensure the water is no longer boiling or has bubbles and use a skimmer to place the eggs in the water. Then increase the heat back to high and set a timer for 14 minutes.\n2. While the eggs are boiling prepare an ice water bath and set aside. After 14 minutes, remove the eggs from the water and place in the ice water bath.\n3. Once the eggs have cooled completely, peel them and slice in half lengthwise. Remove the yolk to a small bowl with a spoon and place the egg whites on a plate.\n4. Mash the yolks with a fork and add the mayonnaise, mustard, vinegar, salt and pepper. Stir everything together until it's smooth. \n5. Use a spoon to add a portion of the deviled egg mixture back into the hole of each egg white. Sprinkle on paprika for garnish.",
            isFavorite = false
        ),
        Recipe(
            id = 4,
            name = "Cheeseburger",
            imageResId = R.drawable.cheeseburger,
            ingredients = listOf(
                "1 pound ground beef",
                "1 tablespoon Homemade BBQ seasoning",
                "Third of a cup of Shredded cheddar cheese",
                "4 Slices of your favorite cheese",
                "4 Hamburger buns"
            ),
            instructions = "1. Pre heat your grill to medium-high to high heat.\n2. Hand mix the ground beef, BBQ rub and shredded cheese in a mixing bowl. Divide the hamburger into 4 equal sizes. Roll the meat into a ball and flatten to make the patties.\n3. Place the patties on the grill. Flip approximately 5 minutes per side.\n4. Place a slice of cheese on each patty and let sit on the grill. \n5. Once the sliced cheese is slightly melted, serve on a bun with your favorite toppings and condiments.",
            isFavorite = false
        ),
        Recipe(
            id = 5,
            name = "BLT",
            imageResId = R.drawable.blt,
            ingredients = listOf(
                "3 slices bacon",
                "1 small tomato",
                "Kosher salt",
                "Freshly ground black pepper",
                "2 slices hearty white sandwich bread",
                "2 tablespoons mayonnaise",
                "1 romaine lettuce leaf"
            ),
            instructions = "1. Cook the bacon.\n2. Slice and season the tomato.\n3. Toast the bread.\n4. Build the BLT.",
            isFavorite = false
        ),
        Recipe(
            id = 6,
            name = "Scrambled Eggs",
            imageResId = R.drawable.scrambledeggs,
            ingredients = listOf(
                "Eggs",
                "Salt",
                "Ground black pepper",
                "Butter"
            ),
            instructions = "1. Lightly beat the eggs, a third teaspoon salt and a few grinds of black pepper in a medium bowl.\n2. Melt 1 tablespoon of the butter in a medium nonstick skillet over low heat; swirl to coat the bottom and sides. Add the eggs, and cook slowly, scraping them up with a rubber spatula occasionally, until most of the liquid has thickened and the eggs are soft, about 10 minutes. (If you like your eggs a little firmer, cook them for an additional 2 to 3 minutes.) Remove them from the heat, and gently fold in the remaining 1 tablespoon of butter. Serve hot.",
            isFavorite = false
        ),
        Recipe(
            id = 7,
            name = "Brownies",
            imageResId = R.drawable.brownies,
            ingredients = listOf(
                "2 eggs and water",
                "Powdered sugar",
                "Unsweetened cocoa powder",
                "Oil",
                "Vanilla Extract"
            ),
            instructions = "1. Mix together the dry and wet ingredients in two separate bowls.\n2. Next, combine the wet and dry ingredients.\n3. Then, pour the batter into an 8×8 inch baking pan lined with parchment paper.\n4. Transfer the pan to a 325-degree oven and bake for 40 to 45 minutes, until a toothpick inserted comes out with a few crumbs attached.",
            isFavorite = false
        ),
        Recipe(
            id = 8,
            name = "Tacos",
            imageResId = R.drawable.tacos,
            ingredients = listOf(
                "1 pound lean ground beef",
                "1 cup thick & chunky salsa",
                "Half a head lettuce, shredded",
                "1 cup shredded cheddar cheese",
                "1 medium tomato, chopped"
            ),
            instructions = "1. Cook beef in 10-inch skillet over medium heat 8 to 10 minutes, stirring occasionally, until brown; drain.\n2. Stir salsa into beef. Heat to boiling, stirring constantly; reduce heat to medium-low. Cook 5 minutes, stirring occasionally. Pour beef mixture into large serving bowl; cover to keep warm.\n3. Heat taco shells as directed on package. Serve taco shells with beef mixture, lettuce, tomato and cheese.",
            isFavorite = false
        ),
        Recipe(
            id = 9,
            name = "Mashed Potatoes",
            imageResId = R.drawable.mashedpotatoes,
            ingredients = listOf(
                "4 pounds potatoes",
                "3 cloves garlic",
                "A third cup melted salted butter",
                "1 cup milk",
                "Salt and pepper"
            ),
            instructions = "1. Peel and quarter potatoes, place in a pot of cold salted water.\n2. Add cloves of garlic (if using) & bring to a boil, cook uncovered 15 minutes or until fork-tender. Drain well.\n3. Heat milk on the stove top (or in the microwave) until warm.\n4. Add butter to the potatoes and begin mashing. Pour in heated milk a little at a time while using a potato masher to reach desired consistency.\n5. Season with salt and pepper. Serve hot.",
            isFavorite = false
        ),
        Recipe(
            id = 10,
            name = "Fettuccine Alfredo",
            imageResId = R.drawable.fettuccinealfredo,
            ingredients = listOf(
                "24 ounces dry fettuccine pasta",
                "1 cup butter",
                "A third pint heavy cream",
                "1 dash garlic salt",
                "Salt and pepper",
                "Three quarters cup grated Romano cheese",
                "A half cup grated Parmesan cheese"
            ),
            instructions = "1. Bring a large pot of lightly salted water to a boil. Add fettuccine and cook for 8 to 10 minutes or until al dente; drain.\n2. Melt butter into cream in a large saucepan over low heat; add salt, pepper, and garlic salt.\n3. Increase the heat to medium; stir in grated Romano and Parmesan cheese until melted and sauce has thickened.\n4. Add cooked pasta to sauce and toss until thoroughly coated; serve immediately.",
            isFavorite = false
        )
    )
}