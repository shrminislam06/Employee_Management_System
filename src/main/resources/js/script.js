console.log("this is console file");
const search=()=>{
    console.log("searching...");
    let query=$("#search-input").val();
    
    if(query=='')
    {
        $(".search-result").hide();
    }else{
        console.log(query);
        $(".search-result").show();
    }
    let url='http//localhost:8086/search/${query}';
    fetch(url)
    .then((response) => {
        return response.json

    }).then((data) => {
        console.log(data)
    })
};