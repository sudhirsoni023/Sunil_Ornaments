const toggleSidebar = () => {
  if ($(".sidebar").is(":visible")) {
    $(".sidebar").css("display", "none");
    $(".content").css("margin-left", "0%");
  } else {
    $(".sidebar").css("display", "block");
    $(".content").css("margin-left", "20%");
  }
};


const calculateAmountBeforeTax=(event)=>{
	if(event.keyCode == 13){
	event.preventDefault();
	let weight = document.getElementById("weight").value;
	let rate = document.getElementById("rate").value;
	let amountBeforeTax = (rate/1000)*weight;
	document.getElementById("amount").value = amountBeforeTax;
	let cgst = (amountBeforeTax*(1.5/100));
	document.getElementById("cgst").value = cgst ;
	document.getElementById("sgst").value = cgst;
	let net_total = document.getElementById("net_total").value = amountBeforeTax + (cgst*2);
	document.getElementById("total_invoice_value_inwords").value = getText(Math.round(net_total));
	document.getElementById("total_invoice_value_inwords").focus();
}
};


const gotoNextInputForTextArea = (event)=>{
		if(event.keyCode == 40)
		document.getElementById("weight").focus();
};

const goToNextInput = (event)=>{
		if(event.keyCode == 13)
		{
		event.preventDefault();			
		document.getElementById("rate").focus();
		}
}

// functions to convert the given amount into indian word spellings starts here

const numberTexts = {
    0: 'zero',
    1: 'one',
    2: 'two',
    3: 'three',
    4: 'four',
    5: 'five',
    6: 'six',
    7: 'seven',
    8: 'eight',
    9: 'nine',
    10: 'ten',
    11: 'eleven',
    12: 'twelve',
    13: 'thirteen',
    14: 'fourteen',
    15: 'fifteen',
    16: 'sixteen',
    17: 'seventeen',
    18: 'eighteen',
    19: 'nineteen',
    20: 'twenty',
    30: 'thirty',
    40: 'forty',
    50: 'fifty',
    60: 'sixty',
    70: 'seventy',
    80: 'eighty',
    90: 'ninety',
    100: 'hundred',
    1000: 'thousand',
    100000: 'lakhs',
    10000000: 'Crores'
};

const checkLimit = number => {
    const upperLimit = 999999999;
    if (number > upperLimit) {
        throw new Error(`number should be less than ${upperLimit}`);
    }
};

const getLastNDigits = (num, power) => {
    const divider = 10 ** power;
    return [num % divider, ~~(num / divider)];
};

const getTwoDigitWord = digits => {
    let word = '';
    if (digits in numberTexts) {
        word = numberTexts[digits];
    } else {
        const [remaining, tensPlace] = getLastNDigits(digits, 1);
        word = `${numberTexts[tensPlace * 10]} ${numberTexts[remaining]}`;
    }
    return word;
};

const getWordPlace = place => {
    if (place) {
        return numberTexts[10 ** place];
    }
    return '';
}

const getDigitWithPlace = (digit, place) => {
    const wordPlace = getWordPlace(place);
    if (digit < 10) {
        return `${numberTexts[digit]} ${wordPlace}`;
    }
    return `${getTwoDigitWord(digit)} ${wordPlace}`;
}

const getText = number => {
    checkLimit(number);

    let _number = number;
    let currentDigits = _number;

    if (_number in numberTexts) {
        return numberTexts[_number];
    } else {
        const checkFor = [2, 1, 2, 2, 2];
        const places = [0, 2, 3, 5, 7];
        let word = '';

        for (let i in checkFor) {
            [currentDigits, _number] = getLastNDigits(_number, checkFor[i]);
            if (currentDigits) {
                word = `${getDigitWithPlace(currentDigits, places[i])} ${word}`;
            }
            if (!_number) return word.trim();
        }
    }
};
