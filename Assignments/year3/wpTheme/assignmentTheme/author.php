<?php get_header(); ?>

<div id="primary" class="content-area">
  <main id="main" class="site-main">
    <?php
    $author = get_queried_object();
    ?>
    <header class="author-header">
      <h1>Posts by <?php echo esc_html( $author->display_name ); ?></h1>
      <?php if ( get_the_author_meta( 'description', $author->ID ) ) : ?>
        <p class="author-bio"><?php echo esc_html( get_the_author_meta( 'description', $author->ID ) ); ?></p>
      <?php endif; ?>
      <p class="author-meta">
        Website: <a href="<?php echo esc_url( get_the_author_meta( 'user_url', $author->ID ) ); ?>">
          <?php echo esc_html( get_the_author_meta( 'user_url', $author->ID ) ); ?>
        </a>
      </p>
    </header>

    <?php if ( have_posts() ) : ?>
      <?php while ( have_posts() ) : the_post(); ?>
        <article id="post-<?php the_ID(); ?>" <?php post_class(); ?>>
          <h2><a href="<?php the_permalink(); ?>"><?php the_title(); ?></a></h2>
          <div class="entry-meta">Published on <?php echo get_the_date(); ?></div>
          <div class="entry-excerpt"><?php the_excerpt(); ?></div>
        </article>
      <?php endwhile; ?>

      <div class="pagination">
        <?php the_posts_pagination(); ?>
      </div>
    <?php else : ?>
      <p>No posts yet from this author.</p>
    <?php endif; ?>
  </main>
</div>

<?php get_footer(); ?>