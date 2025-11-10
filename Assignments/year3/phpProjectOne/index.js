// Slider javascript

function minSlider() {
  document.querySelector("#minRange").addEventListener("input", function (e) {
    document.querySelector(".min-range-label").textContent =
      e.currentTarget.value;
    var xmlhttp = new XMLHttpRequest();
    if (xmlhttp.status == 200 && xmlhttp.readyState == 4) {
      xmlhttp.onreadystatechange = function () {
        document.querySelector("#arr-student-marks");
      };
    }
  });
}

document.querySelector("#maxRange").addEventListener("input", function (e) {
  document.querySelector(".max-range-label").textContent = e.currentTarget.value)
