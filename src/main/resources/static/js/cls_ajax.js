document.write('<script src="http://code.jquery.com/jquery-latest.min.js"></script>');

function file_upload(p){
    if (!p.files || !p.loader || !p.path) return new XMLHttpRequest;

    let formData = new FormData();
    for (let i in p.files) {
        formData.append("uploadfile", p.files[i]);
    }
    formData.append("path", p.path);

    return $.ajax({
        url:'/image/upload',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        onprogress: function(e){
            console.log(formData)
            p.fn_progress && p.fn_progress(e);
        },
        success: function(e){
            p.fn_success && p.fn_success(e);
        },
        error: function(e){
            p.fn_error && p.fn_error(e);
        },
        abort: function(e){
            p.fn_abort && p.fn_abort(e);
        }
    });
}