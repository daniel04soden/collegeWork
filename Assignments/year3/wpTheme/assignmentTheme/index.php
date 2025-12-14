<?php get_header(); ?>
<?php
if (have_posts()) :
    while (have_posts()) :
        the_post();
        ?>
            <article id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
                <header >
                    <h2><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
                    
                    <div >
                        Posted on <?php echo get_the_date(); ?> by <?php the_author(); ?>
                    </div>
                </header>
                <div>
                    <?php the_excerpt(); ?> 
                </div>
                <footer>
                    <a href="<?php the_permalink(); ?>">Read More &raquo;</a>
                </footer>
            </article>
            <?php
    endwhile;
else :
    ?>
        <p>So sad no posts</p>
        <?php
endif;
?>
<?php get_footer(); ?>
