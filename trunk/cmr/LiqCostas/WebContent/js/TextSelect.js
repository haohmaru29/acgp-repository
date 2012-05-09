var is_gecko = /gecko/i.test(navigator.userAgent);
var is_ie    = /MSIE/.test(navigator.userAgent);
var istart = 0;
var iend = 0;			

function setSelectionRange(input, start, end) {
	if (input.setSelectionRange) {
		input.setSelectionRange(start, end);
	} else {
		// assumed IE
		var range = input.createTextRange();
		range.collapse(true);
		range.moveStart("character", start);
		range.moveEnd("character", end - start);
		range.select();
	}
};

function getSelectionStart(input) {
	
	if (input.selectionStart)
		return input.selectionStart;
	return input.value.length; //no implementada para explorer	
/*	var range = document.selection.createRange();	
	var isCollapsed = range.compareEndPoints("StartToEnd", range) == 0;
	if (!isCollapsed)
		range.collapse(true);
	var b = range.getBookmark();

	return b.charCodeAt(2) - 2;
*/
};


function getSelectionEnd(input) {
	if (input.selectionEnd)
		return input.selectionEnd;
	return input.value.length; //no implementada para explorer	
	/*var range = document.selection.createRange();
	var isCollapsed = range.compareEndPoints("StartToEnd", range) == 0;
	if (!isCollapsed)
		range.collapse(false);
	var b = range.getBookmark();
	return b.charCodeAt(2) - 2;
	*/

};

      function inputKey(input, ev) {
        setTimeout(function() {
          istart = getSelectionStart(input);
          iend = getSelectionEnd(input);
        }, 20);
      }
      /*
      function doSelect(input) {
        var start = istart;
        var end = iend;
        input.focus();
        setSelectionRange(input, start, end);
      }
*/