$(function(){
	jQuery.topjectValidate=function topjectValidate(formId){
	    $('#'+formId).formValidation({
	    	message:'请输入有效的值',
	 	        icon: {
	 	            valid: 'glyphicon glyphicon-ok',
	 	            invalid: 'glyphicon glyphicon-remove',
	 	            validating: 'glyphicon glyphicon-refresh'
	 	        }
	    });
	};
	
	jQuery.topjectIsValidate=function topjectIsValidate(formId){
		return $('#'+formId).data('formValidation').validate().isValid();
	};
	
});


