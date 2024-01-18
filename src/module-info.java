module kryptonbutterfly.bounded
{
	exports kryptonbutterfly.bounded;
	exports kryptonbutterfly.bounded.presets._bool;
	exports kryptonbutterfly.bounded.presets._byte;
	exports kryptonbutterfly.bounded.presets._short;
	exports kryptonbutterfly.bounded.presets._int;
	exports kryptonbutterfly.bounded.presets._long;
	exports kryptonbutterfly.bounded.presets._float;
	exports kryptonbutterfly.bounded.presets._double;
	
	requires transitive kryptonbutterfly.Monads;
	requires transitive kryptonbutterfly.Functional;
}