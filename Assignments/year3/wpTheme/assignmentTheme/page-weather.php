<?php
/*
template name: Weather Page
*/
?>

<?php get_header(); ?>

<div class="weather_page_content">
    <div>
        <?php
    $lat = get_theme_mod("get_lat", 51.92);
    $long = get_theme_mod("get_long",-8);
    $weather_call = sprintf("https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&daily=rain_sum&timezone=auto", $lat, $long);
    echo "<div>Your Latitude: $lat</div>";
    echo "<div>Your Longitude: $long</div>";

    $json_data = file_get_contents($weather_call);
    $weather_data = json_decode($json_data);
    $weather_week = $weather_data->daily->time;
    $rain = $weather_data->daily->rain_sum;
    $city = get_theme_mod("city_ID", "Dublin");

?>
    <table>
        <thead>
            <tr>
                <th><?php echo $city; ?> Rain Forecast?</th>
            </tr>
        </thead>
        <tbody>
            <?php
            foreach ($weather_week as $index => $day) {
                $rain_check = check_rain($rain, $index);
                $day_of_week = determine_day($index);
                echo "<tr><td>" . $day_of_week . " - " . $rain_check . "</td></tr>";
            }
            ?>
        </tbody>
    </table>


    </div>
    <div class="right"></div>

</div>

<?php get_footer(); ?>
