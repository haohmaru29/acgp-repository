function trim(value) {
   var temp = value;
   var obj = /^(\s*)([\W\w]*)(\b\s*$)/;
   if (obj.test(temp)) { temp = temp.replace(obj, '$2'); }
   var obj = /  /g;
   while (temp.match(obj)) { temp = temp.replace(obj, ""); }
   return temp;
}

function agrega(e, obj2, obj1){
  if (e.keyCode == 13 && trim(obj1.value) != '')
  {
    obj2.options[obj2.options.length] = new Option(obj1.value, obj1.value, false);
    obj1.value = '';
  }
}

function quita(obj2, obj1)
{
  var arr = new Array();
  var j = 0;
  obj1.value = obj2.value;
  for (i = 0; i < obj2.length; i++){
    if (i != obj2.selectedIndex){
      arr[j] = obj2.options[i];
      j++;
    }
  }
  obj2.length = 0;
  for (i = 0; i < arr.length; i++){
    obj2.options[i] = arr[i];
  }
}
