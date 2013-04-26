/**
 * Retrieves the url arguments. Specifically the question number.
 */
var urlParams = {};
(function () {
    var match,
        pl     = /\+/g,  // Regex for replacing addition symbol with a space
        search = /([^&=]+)=?([^&]*)/g,
        decode = function (s) { return decodeURIComponent(s.replace(pl, " ")); },
        query  = window.location.search.substring(1);

    while (match = search.exec(query))
       urlParams[decode(match[1])] = decode(match[2]);
})();

var qid = urlParams["qid"];
var question;

$.get("getQuestion.php", {qid: qid}, jsonParseQuestion);

/**
 * Takes in the question retrieved from an AJAX post and parses it into JSON
 * then continues with the script. Since AJAX is asynchronous, anything after
 * the post to get the question must be called from this function.
 * @param {Object} data
 */
function jsonParseQuestion(data) {
	question = JSON.parse(data);
	afterHaveQuestion();
}

function afterHaveQuestion() {
	var questionDiv = document.getElementById("leftSide");
	questionDiv.innerHTML = question.question;
	var type = question.type;
	if (type === "mc"){
		createMultipleChoice();
	} else if (type === "ma") {
		createMatching();
	} else if (type === "se") {
		createSelectAll();
	}
}

function createMultipleChoice() {
	var choices = question.choices;
	var comments = question.feedback;
	var answer = question.answers[0];
	var buttons = new Array();
	for (var i = 0; i < choices.length; i++) {
		var choice = choices[i];
		if (comments.length == 2) {
			if (choice == answer) {
				buttons.push(createMCButton(choice, comments[0]));
			} else {
				buttons.push(createMCButton(choice, comments[1]));
			}
		} else {
			buttons.push(createMCButton(choice, comments[i]));
		}
	}
	var tbl = document.getElementById("rightSide");
	buttons.sort(function() {return 0.5 - Math.random()})
	for (var i = 0; i < buttons.length; i++) {
		var button = buttons[i];
		var lastRow = tbl.rows.length;
		var row = tbl.insertRow(lastRow);
		var cell1 = row.insertCell(0);
		cell1.appendChild(button);
	}
}

function createMatching() {
	var answers = question.answers;
	var choices = question.choices;
	var comments = question.feedback;
	var tbl = document.getElementById("rightSide");
	var answerMap = {} //used to keep track of which choice each answer matches to
	for(var i = 0; i < answers.length; i++) {
		var answer = answers[i];
		answerMap[i] = answer;
	}
	var selects = {};
	answers.sort(function() {return 0.5 - Math.random()}) // randomizes the order of the answers
    for(var i = 0; i < choices.length; i++) {
    	//creates the new row
    	var lastRow = tbl.rows.length;
		var row = tbl.insertRow(lastRow);
		//creates 5 horizontal cells placed in the new row
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);
		var cell4 = row.insertCell(3);
		var cell5 = row.insertCell(4);
		//assign the first cell to be the indice
		cell1.innerHTML = i+1;
		//assign the second cell to contain the choice text
		var choice = choices[i];
		cell2.innerHTML = choice;
		//the fourth cell contains the combobox (called a 'select' in html)
		var select = createSelect(choices.length);
		cell4.appendChild(select);
		//append a random choice of the answers to the fifth cell
		var answer = answers[i];
		cell5.innerHTML = answer;
		selects[answer] = select;
    }
    var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	row.insertCell(0);
	row.insertCell(1);
	var cell1 = row.insertCell(2);
	var submitButton = document.createElement('input');
	submitButton.setAttribute('type','button');
	submitButton.setAttribute('name',"Submit");
	submitButton.setAttribute('value',"Submit");
	submitButton.onclick = function() {
		
		for (var i = 0; i < choices.length; i++) {
			var answer = answerMap[i];
			if (selects[answer].value != i+1) {
				popupComment(comments[1]);
				return;
			}
		}
		popupComment(comments[0]);
	}
	cell1.appendChild(submitButton);
}
function createSelectAll() {
	var answers = question.answers;
	var choices = question.choices;
	var tbl = document.getElementById("rightSide");
	var choiceMap = {};
	for (var i = 0; i < choices.length; i++) {
		var bool = false;
		for (var j = 0; j < answers.length; j++) {
			if (choices[i] == answers[j]) {
				bool = true;
			}
		}
		choiceMap[choices[i]] = bool;
	}
	choices.sort(function() {return 0.5 - Math.random()}) // randomizes the order of the answers
	var checkboxMap = {};
	var checkboxes = {};
    for(var i = 0; i < choices.length; i++) {
    	//creates the new row
    	var lastRow = tbl.rows.length;
		var row = tbl.insertRow(lastRow);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var choice = choices[i];
		var checkbox = createCheckBox();
		var text = document.createTextNode(choice);
		cell1.appendChild(text);
		cell2.appendChild(checkbox);
		checkboxMap[i] = choiceMap[choice];
		checkboxes[i] = checkbox;
    }
    var lastRow = tbl.rows.length;
	var row = tbl.insertRow(lastRow);
	var cell1 = row.insertCell(0);
	var submitButton = document.createElement('input');
	submitButton.setAttribute('type','button');
	submitButton.setAttribute('name',"Submit");
	submitButton.setAttribute('value',"Submit");
	submitButton.onclick = function() {
		for (var i = 0; i < choices.length; i++) {
			var cb = checkboxes[i];
			if (cb.checked != checkboxMap[i]) {
				popupComment(question.feedback[1]);
				return;
			}
		}
		popupComment(question.feedback[0]);
	}
	cell1.appendChild(submitButton);
}
function popupComment(text) {
	var popup = window.open(null, null, "width=800,height=500,status=0,menubar=0,scrollbars=auto,toolbar=0,titlebar=0");
	popup.document.write(text);
}
function createMCButton(choice, comment) {
	var buttonnode= document.createElement('input');
	buttonnode.setAttribute('type','button');
	buttonnode.setAttribute('name',comment); // use the name to pass the comment value
	buttonnode.setAttribute('value',choice);
	buttonnode.onclick = onClickMC;
	return buttonnode;
}
function onClickMC() {
	popupComment(this.name);
}
function createSelect(numChoices) {
	var select = document.createElement("select");
   	select.setAttribute("name", "mySelect");
   	select.setAttribute("id", "mySelect");
   	select.style.width = "40px";
   	/* setting an onchange event */
   	select.onchange = function() {dbrOptionChange()};
   	var option;
    for (var i = 0; i < numChoices; i++) {
    	option = document.createElement("option");
   		option.setAttribute("value", i+1);
   		option.innerHTML = i+1;
   		select.appendChild(option);
    }
    return select;
}
function createCheckBox(text) {
	var checkbox   = document.createElement('input');
  	checkbox.type = "checkbox";
    checkbox.id = "id";
    checkbox.value = "test";
    checkbox.checked = false;
  	return checkbox;
}