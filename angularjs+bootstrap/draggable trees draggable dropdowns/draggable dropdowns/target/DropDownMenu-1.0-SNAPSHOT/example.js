angular.module('ui.bootstrap.demo', ['ui.bootstrap', 'ui.tree', 'ui.sortable']);
angular.module('ui.bootstrap.demo').controller('DropdownCtrl', function ($scope) {
    $scope.items = [
        'The first choice!',
        'And another choice for you.',
        'but wait! A third!'
    ];
    $scope.status = {
        isopen: false
    };
    $scope.toggled = function (open) {
        console.log('Dropdown is now: ', open);
    };
    $scope.toggleDropdown = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.status.isopen = !$scope.status.isopen;
    };
    $scope.avatars = ['cutlery', 'eye-open', 'flag', 'flash', 'glass', 'fire'];
    var initString = '--Presets--';
    $scope.selected = initString;
    $scope.getAvatar = function () {
        return 'glyphicon glyphicon-remove';
    };
    $scope.open = function () {
        isopen = true;
    };
    $scope.clicked = function (avatar, $event) {
        for (var i = 0; i < $scope.avatars.length; i++)
            if ($scope.avatars[i] === avatar) {
                $scope.avatars.splice(i, 1);
                if ($scope.selected === avatar)
                    $scope.selected = initString;
                break;
            }
        console.log($scope.status.isopen);
        $event.preventDefault();
        $event.stopPropagation();
        //$scope.status.isopen = !$scope.status.isopen;
    };
    $scope.setSelected = function (choosed) {
        $scope.selected = choosed;
    };
    $scope.list = [
        {
            "id": 1,
            "title": "1. dragon-breath",
            "items": []
        },
        {
            "id": 2,
            "title": "2. moire-vision",
            "items": [
                {
                    "id": 23,
                    "title": "2. moire-vision.4",
                    "items": []
                },
                {
                    "id": 21,
                    "title": "2. moire-vision.2",
                    "items": []
                },
                {
                    "id": 20,
                    "title": "2. moire-vision.1",
                    "items": []
                },
                {
                    "id": 22,
                    "title": "2. moire-vision.3",
                    "items": []
                }
            ]
        },
        {
            "id": 3,
            "title": "3. unicorn-zapper",
            "items": []
        },
        {
            "id": 4,
            "title": "4. romantic-transclusion",
            "items": []
        }
    ];
    $scope.sortableOptions = {
        activate: function () {
            console.log("activate");
        },
        beforeStop: function () {
            console.log("beforeStop");
        },
        change: function () {
            console.log("change");
        },
        create: function () {
            console.log("create");
        },
        deactivate: function () {
            console.log("deactivate");
        },
        out: function () {
            console.log("out");
        },
        over: function () {
            console.log("over");
        },
        receive: function () {
            console.log("receive");
        },
        remove: function () {
            console.log("remove");
        },
        sort: function () {
            console.log("sort");
        },
        start: function () {
            console.log("start");
        },
        update: function (e, ui) {
            console.log("update");
            var logEntry = tmpList.map(function (i) {
                return i.value;
            }).join(', ');
            $scope.sortingLog.push('Update: ' + logEntry);
        },
        stop: function (e, ui) {
            console.log("stop");
            // this callback has the changed model
            var logEntry = tmpList.map(function (i) {
                return i.value;
            }).join(', ');
            $scope.sortingLog.push('Stop: ' + logEntry);
        }
    };

});