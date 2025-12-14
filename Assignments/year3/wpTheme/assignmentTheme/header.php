<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <?php wp_head(); ?>
</head>
<body>
<h1>Daniel Soden - Assignment 2 Website</h1>
    <nav>
    <ul class="navbar">
    <?php wp_list_pages([
            "title_li" => "",
            "depth" => 1,
            "sort_column" => "menu_order",
        ]); ?>
    </ul>
    </nav>
<h2><?php echo " Current Date: " . get_current_date(); ?></h2>
<?php get_search_form(); ?>