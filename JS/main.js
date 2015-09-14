function change()
{
  var x = document.getElementById("mSide");
  x.innerHTML = "New Side!";
}

function getInfo()
{

  var payedV = document.getElementById("payed").value;
  var recievedV = document.getElementById("recieved").value;
  var giveV = document.getElementById("given").value;

  //Param #1, How Much You payed
  //Param #2, How Much You Recieved
  //Param #3, How Much Per Dub
  getInfo(payedV, recievedV, giveV);

}

function calculate(payed, recieved, gPerDub)
{

  var numOfDubs = Math.round(recieved / gPerDub);

  var profit = Math.round(numOfDubs * 20 - payed);

  alert("Dubs: " + numOfDubs + ": Profit: $" + profit);

}
