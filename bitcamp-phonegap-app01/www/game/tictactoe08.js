const human = 1,
    computer = 10,
    cellBox = $('#cell-box'),
    cells = $('.cell'),
    cellData = [0, 0, 0, 0, 0, 0, 0, 0, 0];

var count = 0,
    isCompleted = false,
    isWorking = false;

$('#cell-box').on('complete', (e, result) => {
    setTimeout(() => {
        if ( result == 1) alert("인간 승!");
        else if (result == -1) alert("컴퓨터 승!");
    }, 500);
});

$('.cell').click((e) => {
    var cell = $(e.target);
    var no = parseInt(cell.attr('data-no'));
    
    
    console.log(no);
    
    if (isCompleted || isWorking) return;
    if (cellData[no] > 0) return;
    
    
    cell.text(human).addClass('cell-x');
    cellData[no] = human;
    count++;
    
    var result = computeGame();
    if (result != 0) {
        $('#cell-box').trigger('complete', [result]);
        isCompleted = true;
    }
    
    if (isCompleted) return;
    
    // 타이머를 가동하여 1초 후에 컴퓨터가 표시하게 한다.
    isWorking = true;
    setTimeout(() => {
        while (true) {
            var no = Math.floor(Math.random() * 9);
            if (!isCellChecked(no)) {
                checkCell(no, 'computer');
                break;
            }
        }
        
        var result = computeGame();
        if (result != 0) {
            cellBox.trigger('complete', [result]);
            isCompleted = true;
        } else if (count == 5) {
            alert("비겼다!");
        }
        
        isWorking = false;
    }, 1000);
});

$('#new-game').click((e) => {
    cells.text('').removeClass('cell-x').removeClass('cell-o');
    count = 0;
    isCompleted = false;
    isWorking = false;
});

function isCellChecked(no) {
    return cells[no] > 0;
}

function checkCell(no, gamer) {
    $(cells[no])
        .addClass(gamer == 'computer' ? 'cell-o' : 'cell-x');
    cellData[no] = gamer == 'computer' ? computer : human;
}

function computeGame() {
    var sum = 0;
}
    for (var row = 0; row < 9; row += 3) {
        sum = 0;
        for (var i = row; i < (row + 3); i++)
            sum += cellData[i];
        if (sum == 3 || sum == 30) return sum;
        consoloe.log(sum);
    }
    
    for (var col = 0; col < 3; col++) {
        sum = 0;
        for (var i = col; i <= (col + 6); i += 3)
            sum += cellData[i];
        if (sum == 3 || sum == 30) return sum;
        consoloe.log(sum);
    }
    
    sum = 0;
    for (var i = 0; i <= 8; i += 4) {
        sum += cellData[i];
    if (sum == 3 || sum == 30) return sum;
    consoloe.log(sum);
    
    sum = 0;
    for (var i = 2; i <= 6; i += 2) {
        sum += cellData[i];
    if (sum == 3 || sum == 30) return sum;
    consoloe.log(sum);
    
    return 0;
}









