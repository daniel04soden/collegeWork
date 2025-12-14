
# Sever+Local setup

- Install MAMP/XAMMP - mainly using htdocs folder.
- Using ftp client (filezilla works best) enter these details

1. host: webdevcit.com
2. ftp_(student number)
3. password (given in grade comments in login details advanced web publishing apps)
![[Pasted image 20251208204410.png]]

- From here, transfer wordpress files downloaded onto server and visit url below

	- https://webdevcit.com/2026/yourstudentnumber/nameofpasteddirectory/

- At this point it is likely it will say database connection failed.
- From here edit wp-config-sample.php and paste in the following and adjust login details accordingly
- Once edited, rename file to wp-config.php
```php
<?php

/**
 * The base configuration for WordPress
 *
 * The wp-config.php creation script uses this file during the installation.
 * You don't have to use the website, you can copy this file to "wp-config.php"
 * and fill in the values.
 *
 * This file contains the following configurations:
 *
 * * Database settings
 * * Secret keys
 * * Database table prefix
 * * ABSPATH
 *
 * @link https://developer.wordpress.org/advanced-administration/wordpress/wp-config/
 *
 * @package WordPress
 */

// ** Database settings - You can get this info from your web host ** //
/** The name of the database for WordPress */
define('DB_NAME', 'R00XXXXXX_db');

/** Database username */
define('DB_USER', 'R00XXXXXX_db');

/** Database password */
define('DB_PASSWORD', 'password given in grades');

/** Database hostname */
define('DB_HOST', 'localhost');

/** Database charset to use in creating database tables. */
define('DB_CHARSET', 'utf8');

/** The database collate type. Don't change this if in doubt. */
define('DB_COLLATE', '');

/**#@+
 * Authentication unique keys and salts.
 *
 * Change these to different unique phrases! You can generate these using
 * the {@link https://api.wordpress.org/secret-key/1.1/salt/ WordPress.org secret-key service}.
 *
 * You can change these at any point in time to invalidate all existing cookies.
 * This will force all users to have to log in again.
 *
 * @since 2.6.0
 */
define('AUTH_KEY', 'put your unique phrase here');
define('SECURE_AUTH_KEY', 'put your unique phrase here');
define('LOGGED_IN_KEY', 'put your unique phrase here');
define('NONCE_KEY', 'put your unique phrase here');
define('AUTH_SALT', 'put your unique phrase here');
define('SECURE_AUTH_SALT', 'put your unique phrase here');
define('LOGGED_IN_SALT', 'put your unique phrase here');
define('NONCE_SALT', 'put your unique phrase here');

/**#@-*/

/**
 * WordPress database table prefix.
 *
 * You can have multiple installations in one database if you give each
 * a unique prefix. Only numbers, letters, and underscores please!
 *
 * At the installation time, database tables are created with the specified prefix.
 * Changing this value after WordPress is installed will make your site think
 * it has not been installed.
 *
 * @link https://developer.wordpress.org/advanced-administration/wordpress/wp-config/#table-prefix
 */
$table_prefix = 'wp_';

/**
 * For developers: WordPress debugging mode.
 *
 * Change this to true to enable the display of notices during development.
 * It is strongly recommended that plugin and theme developers use WP_DEBUG
 * in their development environments.
 *
 * For information on other constants that can be used for debugging,
 * visit the documentation.
 *
 * @link https://developer.wordpress.org/advanced-administration/debug/debug-wordpress/
 */
define('WP_DEBUG', false);

/* Add any custom values between this line and the "stop editing" line. */



/* That's all, stop editing! Happy publishing. */

/** Absolute path to the WordPress directory. */
if (! defined('ABSPATH')) {
    define('ABSPATH', __DIR__ . '/');
}

/** Sets up WordPress vars and included files. */
require_once ABSPATH . 'wp-settings.php';

```

- Should be brought to this screen
![[Pasted image 20251208204911.png]]
![[Pasted image 20251208204921.png]]

- Upon entering head to the users tab and create the following users:
![[Pasted image 20251208205052.png]]
- To configure the correct order of pages, once pages are imported via xml through the instructions in his slides, update all of these 1-9 and any leftover pages should be put past 9 in the page order.
![[Pasted image 20251208205120.png]]

- ![[Pasted image 20251208205254.png]]
- In the reading portion of the settings, set the homepage as the frontpage and the posts page as blog. At this point the first 15% should be done. 

- A lot of the remaining can be completed by watching this video while ensuring the functions used are in the notes:

https://www.youtube.com/watch?v=wUz69qRjN2s