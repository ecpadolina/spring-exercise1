$(document).ready(function() {
   
    $("#contactNumber").on("click",".delete", function(){ 
        $(this).parent('div').remove();
    });

    $("#email").click(function(){ 
            $("#contactNumber").append('<div><input type="hidden" name="contactType" value="Email"/>E-mail: ' +
                                       '<input type="text" name="contactInfo"/>'+
                                       '<button class="delete">Remove</button></div>');
    });
   
    $("#mobile").click(function(){ 
            $("#contactNumber").append('<div id="hey"><input type="hidden" name="contactType" value="Mobile"/>Mobile: '+
                                       '<input type="text" name="contactInfo"/>'+
                                       '<button class="delete">Remove</button></div>');
    });

    $("#landline").click(function(){ 
            $("#contactNumber").append('<div id="hey"><input type="hidden" name="contactType" value="Landline"/>Landline: '+
                                       '<input type="text" name="contactInfo"/>'+
                                       '<button class="delete">Remove</button></div>');
    });

});