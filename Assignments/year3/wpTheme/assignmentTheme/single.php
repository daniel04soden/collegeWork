<?php

get_header();
?>

<div>
    <main>
    <?php
    if (have_posts()) :
        while (have_posts()) :
            the_post();
            ?>
            <article id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
                <header>
                    <h1><?php the_title(); ?></h1>
                    
                    <div>
                        Published on <?php echo get_the_date(); ?> by <a href="<?php echo get_author_posts_url(get_the_author_meta('ID')); ?>"><?php the_author(); ?></a>
                    </div>
                </header>
                <div >
                    <?php the_content(); ?> 
                </div>
            </article>
            <?php
        endwhile;
    endif;
?>
        <?php if (get_next_posts_link()) {
            next_posts_link();
        } ?>
        <?php if (get_previous_posts_link()) {
            previous_posts_link();
        } ?>
    </main></div><?php
get_footer();
?>
