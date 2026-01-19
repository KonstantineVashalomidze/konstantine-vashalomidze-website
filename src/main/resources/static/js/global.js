document.addEventListener('DOMContentLoaded', function() {
    const musicToggle = document.getElementById('music-toggle');
    const backgroundMusic = document.getElementById('background-music');

    if (musicToggle && backgroundMusic) {
        // Ensure volume is reasonable and the track doesn't play by default
        backgroundMusic.volume = 0.5;

        musicToggle.addEventListener('click', function(event) {
            event.preventDefault();
            if (backgroundMusic.paused) {
                backgroundMusic.play();
                musicToggle.textContent = 'Music: ON';
            } else {
                backgroundMusic.pause();
                musicToggle.textContent = 'Music: OFF';
            }
        });
    }
});
