module de.tinycodecrank.bounded
{
	exports de.tinycodecrank.bounded;
	exports de.tinycodecrank.bounded.presets._bool;
	exports de.tinycodecrank.bounded.presets._byte;
	exports de.tinycodecrank.bounded.presets._short;
	exports de.tinycodecrank.bounded.presets._int;
	exports de.tinycodecrank.bounded.presets._long;
	exports de.tinycodecrank.bounded.presets._float;
	exports de.tinycodecrank.bounded.presets._double;
	
	requires transitive de.tinycodecrank.Monads;
	requires transitive de.tinycodecrank.Functional;
}