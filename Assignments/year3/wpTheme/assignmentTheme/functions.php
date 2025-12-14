<?php

// Learning for function.php found here  - https://www.youtube.com/watch?v=wUz69qRjN2s&t=594s
function set_up_theme()
{
    add_theme_support("title-tag");
    add_theme_support("post-thumbnails");
}
add_action("after_setup_theme", "set_up_theme");

function my_calendar_widgets_init() {
    register_sidebar( array(
        'name'          => esc_html__( 'Calendar Page Sidebar', 'textdomain' ),
        'id'            => 'calendar-page-sidebar', 
        'description'   => esc_html__( 'Add your Calendar Widget here.', 'textdomain' ),
        'before_widget' => '<section id="%1$s" class="widget %2$s">',
        'after_widget'  => '</section>',
        'before_title'  => '<h2 class="widget-title">',
        'after_title'   => '</h2>',
    ) );
}
add_action( 'widgets_init', 'my_calendar_widgets_init' );
function load_files()
{
    wp_enqueue_style("main-style", get_stylesheet_uri(), array(), '1.1');
}
add_action("wp_enqueue_scripts", "load_files");

function colorscheme_select($wp_customize){
    $wp_customize->add_section('colourscheme_ID',array(
        'title'=>esc_html__('Select a colorscheme','assignmenttheme'),
        'priority' => 3,
        'description'=> esc_html__('Select one of three colorschemes here',
        'assignmenttheme'),
        'description_hidden' => false
    ));


}

function weather_detect( $wp_customize ) {
    $wp_customize->add_section( 'weather_ID' , array(
        'title' => esc_html__('Change Weather Settings', 'assignmenttheme'),
        'priority' => 2,
        'description' => esc_html__('Customise the weather settings here',
            'assignmenttheme'),
        'description_hidden' => false
    ) );

    $wp_customize->add_setting( 'city_ID', array(
        'default' => 'Cork'
    ) );

    $wp_customize->add_control( 'city_ID', array(
        'label' => esc_html__( 'City', 'assignmenttheme' ),
        'description' => esc_html__( 'Set which city you are in' ,
            "assignmenttheme"),
        'section' => 'weather_ID',
        'priority' => 30
    ) );

    $wp_customize->add_setting( 'get_lat', array(
        'default' => 51.92
    ) );

    $wp_customize->add_control( 'get_lat', array(
        'label' => esc_html__( 'Latitude', 'assignmenttheme' ),
        'description' => esc_html__( 'Set your latitude' ,
            "assignmenttheme"),
        'section' => 'weather_ID',
        'priority' => 40
    ) );


    $wp_customize->add_setting( 'get_long', array(
        'default' => -8
    ) );
    $wp_customize->add_control( 'get_long', array(
        'label' => esc_html__( 'Longitude', 'assignmenttheme' ),
        'description' => esc_html__( 'Set longitude' ,
            "assignmenttheme"),
        'section' => 'weather_ID',
        'priority' => 50
    ) );


}
add_action( 'customize_register', 'weather_detect' );
function get_current_date()
{
    return date("Y-m-d");
}


function get_current_year()
{
    return date("Y");
}

function day_of_week($index) {
    $days = array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
    $today_index = date("w");
    $day_index = ($today_index + $index) % 7;
    return $days[$day_index];
}

function determine_day($index) {
    if ($index == 0) {
        return "Today";
    } elseif ($index == 1) {
        return "Tomorrow";
    } else {
        return day_of_week($index);
    }
}
function check_rain($array, $index) {
    if ($array[$index] > 0) {
        return "Rain";
    } else {
        return "No Rain";
    }
}
