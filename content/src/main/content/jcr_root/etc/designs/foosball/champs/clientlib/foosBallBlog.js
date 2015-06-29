$(document).ready(function() {

	
	$(function(){
	     $('#addBlog').click(function(){
	    	 $('#addPost').show();
	    	 $('#mainBlog').hide();
	    });
	     
	     $('#back').click(function(){
	    	 $('#addPost').hide();
	    	 $('#mainBlog').show();
	    });
	 });
	
	$('#posts').load(function() {	
	 $.ajax({
	      url: 'http://localhost:4502/bin/fetchPosts?action=trendingPosts',
	      data: {
	         format: 'json'
	      },
	      error: function() {
	         $('#info').html('<p>An error has occurred</p>');
	      },
	      dataType: 'json',
	      success: function(data) {
	       alert("success");
	      },
	      type: 'GET'
	   });
	});
});


