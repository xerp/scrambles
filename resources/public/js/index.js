const checkApiStatus = () => {
    $.get('/api/ping')
        .done(() => {
            $('.api-status-indicator')
                .removeClass('text-danger')
                .addClass('text-success')
                .html('Good 🚀 😎');

            $('button#verify-button').prop('disabled', false);
        })
        .fail(() => {
            $('.api-status-indicator')
                .removeClass('text-success')
                .addClass('text-danger')
                .html('Bad 💀 😨');

            $('button#verify-button').prop('disabled', true);
        });
};

const verifyScramble = (word1, word2, successCallback) => {
    const data = {word1, word2};

    $.ajax({
        url: '/api/scramble',
        type: 'POST',
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: (request) => {
            successCallback(request);
        }
    });
};

$(() => {
    setInterval(() => {
        checkApiStatus();
    }, 2000);

    $('button#verify-button').click((e) => {
        e.preventDefault();

        const word1 = $('input#word1').val();
        const word2 = $('input#word2').val();

        verifyScramble(word1, word2, (request) => {
            const resultTest = request.result ? 'Yes' : 'No';

            $('div.console > div.results > ul')
                .append(`<li> > Are "${word1}" and "${word2}" scrambled? ${resultTest} </li>`);

            $('input').val('');
        });
    });

    $('button#clear-console-button').click((e) => {
        e.preventDefault();

        $('div.console > div.results').html(`<ul></ul>`);
    });
});