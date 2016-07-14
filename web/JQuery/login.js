
/**
 * Login utilities functions
 */

/**
 * Forces the captcha image refresh, updating code on backend
 * and image on frontend
 * 
 * @returns {undefined}
 */
function reloadCaptcha() {

    var date = new Date();
    $('#captcha').attr('src', 'login/captcha?' + date.getTime());
}

