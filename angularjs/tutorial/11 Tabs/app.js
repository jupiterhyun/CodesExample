(function(){
var app = angular.module('store', []);

app.controller('StoreController', function(){
    this.products = gems;
});

app.controller('TabController', function() {
  	this.tab = 1;
    this.setTab = function(selectedTab){
    	this.tab = selectedTab;
 	 	};
    this.isSet = function(givenTab) {
    	return this.tab === givenTab;
    };
  });




var gems = [
    {
    name: 'Doecahedrom',
    price: 2.95,
    description: '...',
    canPurchase:true,
    soldOut:false,
    images:[
        {full:'double.jpg'},{thumb:'muaa.jpg'}
    ]
    },
    {
    name: 'Pentagonal Gem',
    price: 5.95,
    description: '...',
    canPurchase:false,
    soldOut:false
    }
];

})();


