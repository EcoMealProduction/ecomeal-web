
import React, { useState } from 'react';
import { MapPin, Search, Bell, Sliders } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { FoodCard } from '@/components/FoodCard';
import { Badge } from '@/components/ui/badge';

const mockOffers = [
  {
    id: '1',
    title: 'Assorted Pastries & Fresh Bread',
    restaurant: 'Bakery Dulce Vita',
    originalPrice: 85,
    discountedPrice: 25,
    distance: '0.3km',
    timeLeft: '2h left',
    image: 'photo-1618160702438-9b02ab6515c9',
    category: 'Bakery',
    pickupTime: 'Today 18:00-20:00'
  },
  {
    id: '2',
    title: 'Gourmet Sandwich Selection',
    restaurant: 'Green Garden Café',
    originalPrice: 120,
    discountedPrice: 40,
    distance: '0.8km',
    timeLeft: '4h left',
    image: 'photo-1721322800607-8c38375eef04',
    category: 'Café',
    pickupTime: 'Today 19:00-21:00'
  },
  {
    id: '3',
    title: 'Fresh Salad & Soup Combo',
    restaurant: 'Healthy Corner',
    originalPrice: 95,
    discountedPrice: 35,
    distance: '1.2km',
    timeLeft: '1h left',
    image: 'photo-1472396961693-142e6e269027',
    category: 'Healthy',
    pickupTime: 'Today 17:30-19:30'
  }
];

const categories = ['All', 'Bakery', 'Café', 'Restaurant', 'Supermarket', 'Healthy'];

const Home = () => {
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [favorites, setFavorites] = useState<Set<string>>(new Set());

  const handleReserve = (id: string) => {
    console.log('Reserving offer:', id);
    // Navigate to product detail or checkout
  };

  const handleToggleFavorite = (id: string) => {
    const newFavorites = new Set(favorites);
    if (newFavorites.has(id)) {
      newFavorites.delete(id);
    } else {
      newFavorites.add(id);
    }
    setFavorites(newFavorites);
  };

  const filteredOffers = selectedCategory === 'All' 
    ? mockOffers 
    : mockOffers.filter(offer => offer.category === selectedCategory);

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-green-600 text-white p-4 pt-8">
        <div className="flex items-center justify-between mb-4">
          <div className="flex items-center gap-2">
            <MapPin size={20} />
            <span className="font-medium">Chisinau, Moldova</span>
          </div>
          <Button variant="ghost" size="sm" className="text-white">
            <Bell size={20} />
          </Button>
        </div>
        
        <h1 className="text-2xl font-bold mb-2">Good evening!</h1>
        <p className="text-green-100 mb-4">Find delicious surplus food near you</p>
        
        {/* Search Bar */}
        <div className="relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" size={20} />
          <Input 
            placeholder="Search restaurants, food types..." 
            className="pl-10 pr-12 bg-white text-gray-900 border-0"
          />
          <Button 
            size="sm" 
            variant="ghost" 
            className="absolute right-1 top-1/2 transform -translate-y-1/2 text-gray-600"
          >
            <Sliders size={18} />
          </Button>
        </div>
      </div>

      {/* Impact Banner */}
      <div className="bg-orange-50 border-l-4 border-orange-400 p-4 m-4 rounded-r-lg">
        <div className="flex items-center justify-between">
          <div>
            <p className="text-sm font-medium text-orange-800">Your Impact This Month</p>
            <p className="text-xs text-orange-600">12 meals saved • 5.2kg CO₂ reduced</p>
          </div>
          <div className="text-2xl">🌱</div>
        </div>
      </div>

      {/* Categories */}
      <div className="px-4 mb-4">
        <div className="flex gap-2 overflow-x-auto pb-2">
          {categories.map((category) => (
            <Badge
              key={category}
              variant={selectedCategory === category ? "default" : "outline"}
              className={`cursor-pointer whitespace-nowrap ${
                selectedCategory === category 
                  ? 'bg-green-600 hover:bg-green-700' 
                  : 'hover:bg-gray-100'
              }`}
              onClick={() => setSelectedCategory(category)}
            >
              {category}
            </Badge>
          ))}
        </div>
      </div>

      {/* Food Offers */}
      <div className="px-4 space-y-4">
        <div className="flex items-center justify-between">
          <h2 className="text-lg font-semibold text-gray-900">
            Available Now ({filteredOffers.length})
          </h2>
          <Button variant="ghost" size="sm" className="text-green-600">
            View all
          </Button>
        </div>
        
        {filteredOffers.map((offer) => (
          <FoodCard
            key={offer.id}
            offer={offer}
            onReserve={handleReserve}
            onToggleFavorite={handleToggleFavorite}
            isFavorite={favorites.has(offer.id)}
          />
        ))}
      </div>

      {/* Community Section */}
      <div className="px-4 py-6 mt-8 bg-white">
        <h2 className="text-lg font-semibold text-gray-900 mb-4">
          Community Impact
        </h2>
        <div className="grid grid-cols-2 gap-4">
          <div className="bg-green-50 p-4 rounded-lg text-center">
            <div className="text-2xl font-bold text-green-600">2,847</div>
            <div className="text-sm text-green-700">Meals Saved</div>
          </div>
          <div className="bg-orange-50 p-4 rounded-lg text-center">
            <div className="text-2xl font-bold text-orange-600">1.2T</div>
            <div className="text-sm text-orange-700">CO₂ Prevented</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
