package scalariform.formatter

import scalariform.formatter.preferences._
import scalariform.parser._

// format: OFF
class NestedAnonymousFunctionsTest extends AbstractFormatterTest {

  implicit val formattingPreferences = FormattingPreferences
    .setPreference(NewlinesAtNestedAnonymousFunctions, Force)
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, true)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)
    .setPreference(AlignArguments, true)

   """def bar: Int => Int => String = {
      |  x: Int =>
      |    y: Int =>
      |      "x"
      |}""" ==>
   """def bar: Int => Int => String = {
      |  x: Int =>
      |    y: Int =>
      |      "x"
      |}"""

//  format: ON

  override val debug = false

  def parse(parser: ScalaParser) = parser.nonLocalDefOrDcl()

  type Result = FullDefOrDcl

  def format(formatter: ScalaFormatter, result: Result) = formatter.format(result)(FormatterState())

}
