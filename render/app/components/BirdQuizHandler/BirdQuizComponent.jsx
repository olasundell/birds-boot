'use strict';

var React = require('react');

module.exports = React.createClass({
	displayName: 'BirdQuizHandler',
	mixins: [Router.State, FluxibleMixin, HandlerMixin],
	statics: {
		handlerActions: [LoadAction],
		storeListeners: ['FirstpageStore']
	},
	onChange: function () { this.forceUpdate(); },
	render: function () {

		var store = this.getStore('FirstpageStore');
		var context = this.props.context;
		var loading = !store.isLoaded();

		if(loading) {
			return <PageLoader/>;
		}

		if (context.isDevelopment()) {
			return (
					<div>
					<NewsTopStory model={store.getTopNews()}/>
					<Hero model={store.getHero()}/>
					<Top model={store.getTopEditorials()}/>
					<News model={store.getNews()}/>
					<Bottom model={store.getEditorials()}/>
					</div>
				   );
		}

		return (
				<div>
				<NewsTopStory model={store.getTopNews()}/>
				<Hero model={store.getHero()}/>
				<Top model={store.getTopEditorials()}/>
				<News model={store.getNews()}/>
				<Bottom model={store.getEditorials()}/>
				</div>
			   );
	}

});
