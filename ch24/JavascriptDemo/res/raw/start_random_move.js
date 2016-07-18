<script language="javascript">
var timer;
function startTimer(){
  timer= setInterval("randomMoveButton()",2000); 
}
function randomMoveButton()
{
   var x = Math.round(Math.random() * 300);
   var y = Math.round(100 + Math.random() * 300);

   window.demo.move(x, y);
 
}

startTimer();
</script>
