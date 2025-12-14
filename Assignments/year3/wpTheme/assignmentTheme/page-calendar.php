<?php
get_header();
?>
<div>
    <main>

        <?php
        if (have_posts()):
            while (have_posts()):
                the_post();
                ?>
                <article id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
                    <header>
                        <h1>Title: <?php the_title(); ?></h1>
                    </header>

                    <div>
                        <?php the_content(); ?>

                        <?php
                        wp_link_pages(array(
                            'before' => '<div >Pages:',
                            'after' => '</div>',
                        ));
                ?>
                        <?php
                $child_pages = get_pages(array(
                    'child_of' => get_the_ID(),
                    'sort_column' => 'menu_order'
                ));
                if (!empty($child_pages)) {
                    echo '<div ><h3>Sub Pages:</h3><ul>';
                    foreach ($child_pages as $page) {
                        echo '<li><a href="' . get_permalink($page->ID) . '">' . $page->post_title . '</a></li>';
                    }
                    echo '</ul></div>';
                }
                ?>
                    </div>
                </article>

                <?php
            endwhile;

        endif;
?>

    </main>
</div>
<?php if (is_active_sidebar('calendar-page-sidebar')) : ?>
    <aside id="calendar-sidebar">
        <?php dynamic_sidebar('calendar-page-sidebar'); ?>
    </aside>
<?php endif; ?>

<?php
get_footer();
?>