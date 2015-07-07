$(document).ready(function() {
	$('#addPostBtn').click(function() {
		console.log("Add Post button clicked");
		$('#addPost').show();
		$('#mainBlog').hide();
	});

	$('#backBtn').click(function() {
		$('#addPost').hide();
		$('#mainBlog').show();
	});

	$(document).on("click", "#likeBtn", function() {
		var postId = $(this).siblings("#postId")[0].value;
        var screenNameObj = $(this).siblings("#screenName");
		var screenName = screenNameObj[0].value;
		console.log("Like button clicked :" + screenName);
		if($.trim(screenName) == "") {
			alert("Please enter screen name");
			return false;
		}
		console.log(postId);
		$.ajax({
			url: '/bin/likeCount',
			method: 'POST',
			data: {
				'postId' : postId,
				'screenName' : screenName
			},
			context: document.body,
			success: function(data) {
				console.log("Success method", data);
				if(parseInt(data) > 0) {
					$("#likes" + postId.replace('#', '-')).val(data);
					screenNameObj[0].value = "";
				} else {
					alert(screenName + " already liked the post.");
				}
			},
			error: function() {
				$('#info').html('<p>An error has occurred</p>');
			}
		});
	});
	
	$(document).on("click", "#commentBtn", function() {
		console.log("Fetching the comments", $(this).siblings("#postId")[0]);
		var postId = $(this).siblings("#postId")[0].value;
		var postIdEncoded = postId.replace('#', '%23');
		console.log(postId);
		$.ajax({
			url: '/bin/fetchComments?postId=' + postIdEncoded,
			method: 'GET',
			context: document.body,
			success: function(data) {
				console.log("Success method", data);
				var commentList = $.parseJSON(data);
				var commentTags = "";
				$.each(commentList, function(key, value) {
					var commentListTag = "";
					$.each(value, function(k, v) {
						if(k == "screenName") {
							commentListTag += "<h6>" + v + "</h6>";
						} else if(k == "message") {
							commentListTag += "<p>" + v + "</p>";
						} else {
							// Comment ID
							console.log("CommentId : " + v);
						}
					});
					commentTags += commentListTag;
				});

				$('#comments'+ postId.replace('#', '-')).html(commentTags).css('display', 'block');
			},
			error: function() {
				$('#info').html('<p>An error has occurred</p>');
			}
		});
	});

	$.ajax({
		url: '/bin/fetchPosts?action=trendingPosts',
		method: 'GET',
		context: document.body,
		success: function(data) {
			console.log("Success method");
			console.log(data);
			var postList = $.parseJSON(data);
			var postListTag = "";
			$.each(postList, function(key, value) {
				var postTag = "<div class=\"post\">";
                var postId = value["postId"];
                postId = postId.replace('#', '-');
                console.log(value);
				$.each(value, function(k, v) {
					if(k == "title") {
						postTag += "<h3>" + v + "</h3>";
					} else if(k == "screenName") {
						postTag += "<h6>" + v + "</h6>";
					} else if(k == "postArticle") {
						postTag += "<p>" + v + "</p>";
					} else if(k == "likeCount") {
						postTag += "<input type=\"text\" class=\"likes\" id=\"likes" + postId + "\" value=\"" + v + "\" size=\"5\" disabled><input type=\"text\" id=\"screenName\" placeholder=\"Enter Screen Name\"/><input type=\"button\" id=\"likeBtn\" value=\"Like\"/>";
					} else if(k == "comments") {
						// Code for comments
						postTag += "<input type=\"button\" id=\"commentBtn\" value=\"Comments\"/>";
					} else if(k == "postId") {
						postTag += "<div class=\"comments\" id=\"comments" + postId + "\"></div><input type=\"hidden\" id=\"postId\" value=\"" + v + "\"/>";
					}
				});
				postTag += "</div>";
				postTag += "<hr/>";
				postListTag += postTag;
			});
			$("#trendingPosts").html(postListTag);
		},
		error: function() {
			$('#info').html('<p>An error has occurred</p>');
		}
	});
});