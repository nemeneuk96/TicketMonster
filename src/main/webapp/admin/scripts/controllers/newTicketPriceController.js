
angular.module('ticketMonster').controller('NewTicketPriceController', function ($scope, $location, locationParser, TicketPriceResource , ShowResource, SectionResource, TicketCategoryResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.ticketPrice = $scope.ticketPrice || {};
    
    $scope.showList = ShowResource.queryAll(function(items){
        $scope.showSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.id
            });
        });
    });
    $scope.$watch("showSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.show = {};
            $scope.ticketPrice.show.id = selection.value;
        }
    });
    
    $scope.sectionList = SectionResource.queryAll(function(items){
        $scope.sectionSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.name
            });
        });
    });
    $scope.$watch("sectionSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.section = {};
            $scope.ticketPrice.section.id = selection.value;
        }
    });
    
    $scope.ticketCategoryList = TicketCategoryResource.queryAll(function(items){
        $scope.ticketCategorySelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.description
            });
        });
    });
    $scope.$watch("ticketCategorySelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ticketPrice.ticketCategory = {};
            $scope.ticketPrice.ticketCategory.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/TicketPrices/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        TicketPriceResource.save($scope.ticketPrice, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/TicketPrices");
    };
});