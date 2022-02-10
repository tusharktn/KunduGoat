 var movieStore=Ext.create('Ext.data.Store', {
	    id: 'movieID',
	   // autoLoad: true,
	    fields: ['id','title', 'description', 'releaseYear','language','director','rating','specialFeatures'],
	    pageSize:10, // items per page
	    proxy: {
	        type: 'ajax',
	        url: '/sakila-hrc/selectAll', // url that will load data with respect to start and limit params
	        reader: {
	            type: 'json',
	            rootProperty: 'items',
	            totalProperty: 'total'
	        },
       

	    }
	});
	


  movieStore.reload({
	    params: {
	        start: 0,
	        limit: 10
	    }
	});
	
		var language = Ext.create('Ext.data.Store', {
	    fields: ['val', 'name'],
	    data : [
	        {"val":"1", "name":"English"},
	        {"val":"2", "name":"Italian"},
            {"val":"3", "name":"Japanese"},
             {"val":"4", "name":"Mandarin"},
             {"val":"5", "name":"French"},
              {"val":"6", "name":"German"},
             {"val":"7", "name":"Mongolian"},
	       
	    ]
	});
	var rating = Ext.create('Ext.data.Store', {
	    fields: ['val', 'name'],
	    data : [
	        {"val":"pg", "name":"PG"},
	        {"val":"g", "name":"G"},
	        {"val":"pg13", "name":"PG-13"},
	        {"val":"r", "name":"R"},
	       
	    ]
	});

	var specialf = Ext.create('Ext.data.Store', {
	    fields: ['val', 'name'],
	    data : [
	        {"val":"ds", "name":"Deleted Scenes"},
	        {"val":"bs", "name":"Behind the Scenes"},
	        {"val":"t", "name":"Trailers"},
	        {"val":"c", "name":"Commentaries"},
	       
	    ]
	});	
	

var editStore = Ext.create('Ext.data.Store', {
	    fields: ['val', 'name'],
	    data : [
	        {"val":"eStore[0].data.title", "name":"title"},
	        {"val":"H", "name":"Hindi"},
           ]
});


Ext.onReady(function(){
	
	Ext.create('Ext.Panel',{
		  renderTo: Ext.getBody(),
	      items:[frmpanel,gridMovie]
	})
	  
	
	
});


var frmpanel=Ext.create('Ext.form.Panel', {
		
	    // renderTo: document.body,
	    title: 'Advance Movie Search',
	    //width:'100%',
	    fullscreen:true,
        bodyPadding: 25,
	    buttonAlign: 'center',
        layout: {
		   type: 'vbox',
           align: 'center',
		   padding:20,
		   		},
	    items:[
		   {
			   xtype:'form',
			   layout:'hbox',
			   border:false,
			   
			   
			   items:[
				   {
					   xtype:'textfield',
					   fieldLabel:'Movie Name',
		               name:'movieName',
					   padding:10,
					   
					
				   },
				   {
					   xtype:'textfield',
					   fieldLabel:'Director Name',
		               name:'directorName',
					   padding:10
					
				   }
			   ]
			   
	   },
	   {
		      xtype:'form',
		      layout:'hbox',
		      border:false,
		  
		   items:[
			   {
				   xtype:'datefield',
				   fieldLabel:'Release Year',
				   padding:10,
	               name:'releaseYear'
			   },
			   {
				   xtype:'combobox',
				   padding:10,
				   fieldLabel:'Language',
				   store: language,
				   queryMode: 'local',
				   displayField: 'name',
	               name:'language',
				   valueField: 'val',
				  
			   }
		   ]
		   
	   
   },
  
		   ],
		   buttons:[

			   
			   {
			       xtype: 'button',
			       text: 'Reset',
				
			       
			       
			       handler: function () {
			           this.up('form').reset();
			       }
			   }, {
			       xtype: 'button',
			       text: 'Search',
                  click: function(){
	                    var val=this.up('form').getForm().getValues();   


                     Ext.Ajax.request({
	                          loadMask: true,
		    				      scope: this,
							
		    				      url: '/sakila-hrc/search',
                                  params:{
									title: val.title,
									description:val.description ,
									rating:  val.rating ,
									language: val.language,
									director: val.director ,
									specialFeatures: val.specialFeatures ,
									releaseYear:val.releaseYear  ,
									 
                                       }
	                      
	
                        });
	                 },
					
			      
			   }, 
		   ]
	});


var gridMovie=Ext.create('Ext.grid.Panel', {
	    title: 'movieID',
	    //height:'100vh',
	    //width:'100vh',
        id:'gridid',
	      forceFit:true,
	      store: movieStore,
       // renderTo: Ext.getBody(),
	      enableColumnResize:true, 
	      plugins: {
            ptype: 'rowediting',
            clicksToEdit: 2
           },
	    columns: [
	        { text: 'Title', dataIndex: 'title' ,editor: 'textfield'},
	        { text: 'Description', dataIndex: 'description', editor: 'textfield'},
	        { text: 'Release Year', dataIndex: 'releaseYear' },
	        { text: 'Language', dataIndex:'language'},
	        { text:'Director', dataIndex:'director',editor: 'textfield'},
	        { text:'Rating', dataIndex:'rating'},
	        { text:'Special features', dataIndex:'specialFeatures'}
	    ],
	    //,
	    selModel: {
	        checkOnly: false,
	        injectCheckbox: 'first',
	        mode: 'SIMPLE'
	    },
	    selType: 'checkboxmodel',
		
	    dockedItems: [{
	        xtype: 'pagingtoolbar',
	        store: 'movieID', // same store GridPanel is using
	        dock: 'top',
	        displayInfo: true,
	        items: [
		    	  { xtype: 'button', text: 'Add', iconCls:'x-fa fa-plus-circle"',
		    		  
		    		  listeners: {
		                     click: function() {
		                        addWin.show();
		                     }
		                  }
  	    	      },
		    	  { xtype:'button', 
                    text:'<span class="x-fa fa-edit" style="color:blue;"></span>  Edit',
							 listeners: {
		                     click: function() {
			                       var d=Ext.getCmp('gridid').getSelectionModel().getSelection();
                                      Ext.getCmp('editform').getForm().setValues(d[0].data);
			                      
                                 console.log(d[0]);
                                  if(d){
		                        editWin.show();
                                    }
                                  else
                               {
	                              Ext.Msg.alert( 'Please Select a row');
                                }
                          
		                     }
		                  }
						},
		    	  { xtype:'button', text:'<span class="x-fa fa-trash-o" style="color:red;"></span>  Delete',
						 
					      //iconCls: 'x-fa fa-trash-o',
                            tooltip: 'Delete',
		    		  listeners:{
		    			  click:function(){
			
			
			               var mData= gridMovie.getSelectionModel().getSelection();
                          //console.log(mData[0].data);
						  //console.log(mData.length);
			              //var sid =movieStore.fields.getById(id);
                           console.log(mData[0]);
							var i;
							
							for( i=0;i<mData.length;i++){
		    				  Ext.Ajax.request({
		    				      method: 'GET',
		    				      loadMask: true,
		    				      scope: this,
							
		    				      url: '/sakila-hrc/delete',
                                  params:{
									del:mData[i].data.id,
									length:mData.length,
                                   
                                       },

		    				   
		    				 
		    			  });}
                          Ext.toast('Movie Row Deleted Successfully');
                        movieStore.reload();
							
		    		  }
		    	  
		    	  
		    	  }  
		    	  }
		    		
		    	
		    	],
	    }
	    ],
	   

	    //renderTo: Ext.getBody()
	});









         
var adw= Ext.create('Ext.form.Panel',{
		
		  xtype:'form',
         
          id:'form',
          layout:'form',
          width:600,
          height:500,
          closeAction:'close',
         // plain: true,
          
          items: [{
             xtype : 'textfield',
             fieldLabel: 'Title',
             name:'title',
             //valueField:'at',
          },{
             xtype : 'datefield',
             format:"Y",
             fieldLabel: 'Release Year',
			 name:'releaseYear',
			 //valueField:'ar',
          },
			{
             xtype : 'textfield',
             fieldLabel: 'director',
			 name:'director',
		     //valueField:'ad',
          },
			{
                xtype : 'combobox',
                fieldLabel: 'Special Features',
                store: specialf,
			    queryMode: 'local',
			    displayField: 'name',
                name:'specialFeatures',
			// valueField:'as',
          },{
             xtype : 'combobox',
             fieldLabel: 'Rating',
             store: rating,
			    queryMode: 'local',
			    displayField: 'name',
                name:'rating',
			  // valueField: 'val',
          },{
             xtype : 'combobox',
             fieldLabel: 'Language',
             store: language,
			 queryMode: 'local',
		     displayField: 'name',
		   	 name:'language',
			 valueField: 'val',
          },
          {
              xtype : 'textarea',
              fieldLabel: 'Description',
              name:'description',
              //valueField:'ad',
           }
          ],
          
          buttons: [{
             text: 'Save',
                listeners:{
             click: function(){
	            
	          var val=this.up('form').getForm().getValues();
            
                
              console.log(val);
            
					  Ext.Ajax.request({
		    				      method: 'GET',
		    				      loadMask: true,
		    				      scope: this,
							
		    				      url: '/sakila-hrc/ADD',
                                  params:{
									title: val.title,
									description:val.description ,
									rating:  val.rating ,
									language: val.language,
									director: val.director ,
									specialFeatures: val.specialFeatures ,
									releaseYear:val.releaseYear  ,
									 
                                       }

		    				  
		    				 
		    			  }),
		        
	          Ext.toast('Movie Added Successfully');
                movieStore.reload();
                 }}
            
          },{
             text: 'Cancel',
             handler: function(){
                addWin.close();
                 
              //  Ext.Msg.alert('close', 'Add window is closed');
               }
          }],
          buttonAlign: 'center',
      
          })

         


//var eSelectData=gridMovie.getSelectionModel().getSelection();
//console.log(eSelectData[0]);
//abc= function(){
//	var eSelectData=gridMovie.getSelectionModel().getSelection()[0].data;
//	var title='v'
//}
var  edw = Ext.create('Ext.form.Panel',{
			
		  xtype:'form',
          
          id:'editform',
          layout:'form',
          width:600,
          height:500,
          closeAction:'close',
         // plain: true,
          
          items: [{
             xtype : 'hiddenfield',
             fieldLabel: 'Id',
             name:'id',
             
          },{
             xtype : 'textfield',
             fieldLabel: 'Title',
             name:'title',
             
          },{
             xtype : 'datefield',
             format:"Y",
             fieldLabel: 'Release Year',
			 name:'releaseYear',
			 //valueField:'ar',
          },
			{
             xtype : 'textfield',
             fieldLabel: 'director',
			 name:'director',
		     //valueField:'ad',
          },
			{
            xtype : 'combobox',
                fieldLabel: 'Special Features',
                store: specialf,
			    queryMode: 'local',
			    displayField: 'name',
                name:'specialFeatures',
			// valueField:'as',
			// valueField:'as',
          },{
             xtype : 'combobox',
             fieldLabel: 'Rating',
             store: rating,
			    queryMode: 'local',
			    displayField: 'name',
                name:'rating',
			  // valueField: 'val',
          },{
             xtype : 'combobox',
             fieldLabel: 'Language',
             store: language,
			 queryMode: 'local',
		     displayField: 'name',
		   	 name:'language',
			 valueField: 'val',
          },
          {
              xtype : 'textarea',
              fieldLabel: 'Description',
              name:'description',
              //valueField:'ad',
           }
          ],
          
          buttons: [{
             text: 'Save',
                listeners:{
             click: function(){
	            
	          var eVal=this.up('form').getForm().getValues();
            
                console.log(eVal);
             
            
					  Ext.Ajax.request({
		    				      method: 'GET',
		    				      loadMask: true,
		    				      scope: this,
							
		    				      url: '/sakila-hrc/edit',
                                  params:{
	                                id:eVal.id,
									title: eVal.title,
									description:eVal.description ,
									rating:  eVal.rating ,
									language: eVal.language,
									director: eVal.director ,
									specialFeatures: eVal.specialFeatures ,
									releaseYear:eVal.releaseYear  ,
									 
                                       }

		    				  
		    				 
		    			  }),
		 
	        Ext.toast('Movie Edited Successfully');
                movieStore.reload();
                 }}
            
          },{
             text: 'Cancel',
             handler: function(){
                editWin.close();
               // Ext.Msg.alert('close', 'Add window is closed');
            }
          }],
          buttonAlign: 'center',
      
          });


var addWin = new Ext.Window ({
			   title:'Add Film',
              items:[adw],

          });

var editWin= new Ext.Window({
	          
	          title:'Edit Film',
	          items:[edw]
         });