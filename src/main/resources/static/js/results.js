var delayToShowInSeconds = 2;
        var delayToHideGifImageInSeconds = 5;
        var delayToShowTableRowsInSeconds = 3;

        setTimeout(function() {
            document.getElementById('hiddenText2').style.display = 'block';

            setTimeout(function() {
                document.getElementById('gifImage').style.display = 'none';
                document.getElementById('hiddenText2').style.display = 'none';
                document.getElementById('hiddenText').style.display = 'block';
                document.getElementById('hiddenText3').style.display = 'block';
                document.getElementById('scoreboard').style.display = 'block';

                setTimeout(function() {
                    // Select all hidden rows and make them visible
                    var hiddenRows = document.querySelectorAll('#scoreboard .hidden-row');
                    hiddenRows.forEach(function(row) {
                        row.classList.remove('hidden-row');
                    });
                }, delayToShowTableRowsInSeconds * 1000);

            }, delayToHideGifImageInSeconds * 1000);

        }, delayToShowInSeconds * 1000);