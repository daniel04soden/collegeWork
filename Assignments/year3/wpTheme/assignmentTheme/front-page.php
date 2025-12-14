<header>
<?php get_header(); ?>
<h4>This is the front page of my website</h4>
</header>
<?php
while(have_posts()){
    the_post();?>
    <div>
        <h2> 
            <?php the_title(); ?>
        </h2>
    </div>
<?php
}
    
?>

<footer>
<?php get_footer(); ?>
</footer>