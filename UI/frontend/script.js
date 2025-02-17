
document.addEventListener("DOMContentLoaded", function () {
    let currentIndex = 0;
    const totalSlides = 5; // Update this based on the number of slides
    const radioButtons = document.querySelectorAll("input[name='slider']");

    document.getElementById("prevBtn").addEventListener("click", function () {
        currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
        radioButtons[currentIndex].checked = true;
    });

    document.getElementById("nextBtn").addEventListener("click", function () {
        currentIndex = (currentIndex + 1) % totalSlides;
        radioButtons[currentIndex].checked = true;
    });
});
