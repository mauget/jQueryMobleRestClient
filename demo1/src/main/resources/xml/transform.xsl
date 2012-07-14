<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="INVOICEList">
		<div>
			<h2>Invoices</h2>
			<table>
				<tr bgcolor="#9acd32">
					<th>Number</th>
				</tr>
				<xsl:for-each select="INVOICE">
					<tr>
						<td>
							<xsl:value-of select="." />
						</td>
					</tr>
				</xsl:for-each>
			</table>
		</div>
	</xsl:template>
</xsl:stylesheet>