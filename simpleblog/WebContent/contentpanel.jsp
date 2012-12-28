<div class="col1">
	<!-- Column 1 start -->
	<h2>Percentage dimensions of the left menu layout</h2>
	<img height="370" width="350" alt="Two column left menu layout dimensions" src="http://matthewjamestaylor.com/blog/perfect-2-column-left-menu-dimensions.gif"/>
	<p>All the dimensions are in percentage widths so the layout adjusts to any screen resolution. Vertical dimensions are not set so they stretch to the height of the content.</p>
	<h3>Maximum column content widths</h3>
	<p>To prevent wide content (like long URLs) from destroying the layout (long content can make the page scroll horizontally) the column content divs are set to overflow:hidden. This chops off any content that is wider than the div. Because of this, it's important to know the maximum widths allowable at common screen resolutions. For example, if you choose 800 x 600 pixels as your minimum compatible resolution what is the widest image that can be safely added to each column before it gets chopped off? Here are the figures:</p>
	<dl>
		<dt><strong>800 x 600</strong></dt>
		<dd>Left column: 162 pixels</dd>
		<dd>Main page: 550 pixels</dd>
		<dt><strong>1024 x 768</strong></dt>
		<dd>Left column: 210 pixels</dd>
		<dd>Main page: 709 pixels</dd>
	</dl>
	<h2>The nested div structure</h2>
	<p>I've colour coded each div so it's easy to see:</p>
	<img height="369" width="350" alt="Two column left menu layout nested div structure" src="http://matthewjamestaylor.com/blog/perfect-2-column-left-menu-div-structure.gif"/>
	<p>The header, colmask and footer divs are 100% wide and stacked vertically one after the other. Colleft is inside colmask. The two column content divs (col1 &amp; col2) are inside colleft. Notice that the main content column (col1) comes before the side column.</p>
	<!-- Column 1 end -->
</div>