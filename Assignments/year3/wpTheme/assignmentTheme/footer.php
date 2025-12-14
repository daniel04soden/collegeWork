
<?php
$current_page = get_the_title(); 
?>
<hr>
<?php 

echo "<p>Page: $current_page, © Copyright Daniel Soden " . get_current_year() . "</p>";?>

<?php wp_footer(); ?>
</body>
</html>