
function hideMessage() {
	setTimeout(function() {
		$("#info").hide(500);
	}, 2500);
}

$(document).ready(function() {
    $("#upload-file-input").on("change", uploadFile);
  });
  
  /**
   * Upload the file sending it via Ajax at the Spring Boot server.
   */
  function uploadFile() {
    $.ajax({
      url: "/uploadFile",
      type: "POST",
      data: new FormData($("#upload-file-form")[0]),
      enctype: 'multipart/form-data',
      processData: false,
      contentType: false,
      cache: false,
      success: function () {
        // Handle upload success
        $("#upload-file-message").text("File succesfully uploaded");
      },
      error: function () {
        // Handle upload error
        $("#upload-file-message").text(
            "File not uploaded (perhaps it's too much big)");
      }
    });
  } // 