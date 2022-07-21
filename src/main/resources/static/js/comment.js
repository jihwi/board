

function renderCommentList(){
    if(commentList != null && commentList.length > 0){
        for(var comment of commentList){
           this.setCommentConts(comment);

        }

    }
}

function setCommentConts(comment){
    const commentDiv = template.clone();
    commentDiv.removeClass('hidden');
    commentDiv.attr('id', comment.no);
    commentDiv.find('.insId').text(comment.insId);
    commentDiv.find('.conts').text(comment.conts);
    if(comment.replyNo != null && comment.replyNo !== 0){
        $('#'+comment.replyNo).append(commentDiv);
        return;
    }
    $('.comment_container').append(commentDiv);
}


function replyComment(){
    const target = event.target;
    const commentNo = target.parentNode.id;
    $('.replyNo').val(commentNo);
}